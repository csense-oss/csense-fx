package csense.javafx.views.base

import csense.javafx.extensions.StageExtensions.stageWith
import csense.javafx.extensions.parent.addToFront
import csense.javafx.extensions.parent.replace
import csense.javafx.extensions.showFluent
import csense.javafx.extensions.updateXYFrom
import csense.javafx.observable.addObserverF
import csense.javafx.tracking.BaseViewTracker
import csense.javafx.tracking.logTimingString
import csense.javafx.tracking.onClosing
import csense.javafx.tracking.onStart
import csense.javafx.views.OutputViewAble
import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.annotations.sideEffect.NoEscape
import csense.kotlin.annotations.threading.InAny
import csense.kotlin.annotations.threading.InBackground
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.coroutines.*
import csense.kotlin.extensions.tryAndLog
import csense.kotlin.logger.L
import csense.kotlin.logger.debug
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.stage.Window
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


/**
 *
 * This Guy will contain all the necessary low level stuff;
 *
 * This includes the "bouncing" feature of "threading" / coroutines;
 * The point of Bouncing can be seen in the Bouncing.md file
 */
abstract class AbstractBaseView<ViewBinding : LoadViewAble<Parent>> :
        ToUi<ViewBinding>,
        ToBackground<ViewBinding> {

    internal val tracker = BaseViewTracker().addObserverF {
        L.debugLazy(this::class.java.simpleName) { it.logTimingString() }//TODO use extensions when available!!!
    }

    @InUi
    private var currentStage: Stage? = null
    @InUi
    private var currentWindow: Window? = null

    internal val coroutineScope = BaseViewCoroutineScopeImpl()

    internal abstract fun getViewAsync(): Deferred<ViewBinding>

    //region inUi state transfer
    override fun inUi(@InUi action: InUiUpdateEmptyScope<ViewBinding>): Job = coroutineScope.launchMain {

        action(
                InUiUpdateEmpty(
                        currentWindow,
                        currentStage,
                        getViewAsync().await(),
                        this@AbstractBaseView
                )
        )
    }

    final override fun <T> inUi(
            input: T,
            action: InUiUpdateInputScope<ViewBinding, T>
    ): Job = coroutineScope.launchMain {
        val view = getViewAsync().await()
        action(
                InUiUpdateInput(
                        currentWindow,
                        currentStage,
                        input,
                        view,
                        this@AbstractBaseView
                )
        )
    }

    final override fun <T, Output : Deferred<T>> inUiDeferredAsync(
            @InUi action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<T> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateEmpty(currentWindow, currentStage, view, this@AbstractBaseView)).await()
    }

    final override fun <Output> inUiAsync(
            @InUi action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateEmpty(currentWindow, currentStage, view, this@AbstractBaseView))
    }

    final override fun <Input, Output> inUiAsync(
            input: Input,
            @InUi action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(
                InUiUpdateInput(
                        currentWindow,
                        currentStage,
                        input,
                        view,
                        this@AbstractBaseView
                )
        )
    }

    fun <T> getFromUiAsync(@InUi getter: Function1<ViewBinding, List<T>>): Deferred<List<T>> = inUiAsync {
        getter(binding)
    }

    fun <Dout, T : OutputViewAble<out Dout>> getResultFromControllerAsync(
            @InUi getter: Function1<ViewBinding, T>
    ): Deferred<Dout> = inUiDeferredAsync {
        getter(binding).createResultAsync()
    }
    //endregion


    //region OnBackground
    final override fun inBackground(
            @InBackground action: InBackgroundEmptyScope<ViewBinding>
    ): Job = coroutineScope.launchDefault {
        tryAndLog {
            action(InBackgroundEmpty(this@AbstractBaseView, this))
        }
    }

    final override fun <Input> inBackground(
            input: Input,
            @InBackground action: InBackgroundInputScope<ViewBinding, Input>
    ): Job = coroutineScope.launchDefault {
        action(InBackgroundInput(input, this@AbstractBaseView))
    }

    final override fun <Output> inBackgroundAsync(
            @InBackground action: InBackgroundOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundEmpty(this@AbstractBaseView, coroutineScope))
    }

    final override fun <Input, Output> inBackgroundAsync(
            input: Input,
            @InBackground action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundInput(input, this@AbstractBaseView))
    }

    //    //TODO this is bad. maybe ?
//    internal fun presentThisAsModalInternal(
//            ownerWindow: Window? = null,
//            configureStage: FunctionUnit<Stage>? = null
//    ): Job = inUi(ownerWindow) {
//        isInline = false
//        val newStage = stageWith(binding.root) {
//            //continue the same place as we left off.
//            ownerWindow?.let { ownerWindow ->
//                it.x = ownerWindow.x
//                it.y = ownerWindow.y
//            }
//            configureStage?.invoke(it)
//        }.showFluent()
//        updateWindowAndStage(newStage, newStage)
//        start()
//    }
//

    open fun transitionTo(
            view: AbstractBaseView<*>,
            @InUi configureStage: FunctionUnit<Stage>? = null
    ): Job = inUi {
        executeInUIInOrder(
                view.createInternalNewWindow(currentWindow, configureStage),
                inUi(currentStage) {
                    this.input?.close()
                })
    }

    open fun addToView(toPlaceIn: Pane): Job = inUi(toPlaceIn) {
        isInline = true
        input.addToFront(binding.root)
        updateWindowAndStage(input)
        start()
    }

    open fun replaceToView(container: Pane, viewToReplace: Node) = inUi {
        isInline = true
        container.replace(viewToReplace, binding.root)
        updateWindowAndStage(container)
        start()
    }

    open fun addToScene(toPlaceIn: Scene): Job = inUi(toPlaceIn) {
        isInline = false
        input.root = binding.root
        updateWindowAndStage(input)
        start()
    }

    internal fun createInternalNewWindow(
            window: Window? = null,
            @InUi configureStage: FunctionUnit<Stage>? = null
    ): Job {
        return inUi(window) {
            val bindingRoot = binding.root
            isInline = false
            //TODO preload here ?.. hmm..
            val newStage = stageWith(bindingRoot) {
                //continue the same place as we left off.
                input?.let(it::updateXYFrom)
                configureStage?.invoke(it)
                it.setOnCloseRequest {
                    onClosing()
                }
            }.showFluent()//This call is "slow" / 50 ms on semi warm jvm
            updateWindowAndStage(newStage)
            start()
        }
    }

    @InUi
    internal open fun onClosing() {
        tracker.onClosing()
    }

    /**
     * When window /stage and view should be "ok".
     */
    @InUi
    internal open fun start() {
        tracker.onStart()
    }

    @InUi
    private fun updateWindowAndStage(binding: Scene) =
            updateWindowAndStage(binding.window, binding.window as? Stage)

    @InUi
    private fun updateWindowAndStage(fromPane: Pane) = updateWindowAndStage(
            fromPane.scene?.window,
            fromPane.scene?.window as? Stage
    )

    @InUi
    private fun updateWindowAndStage(stageAndWindow: Stage?) =
            updateWindowAndStage(stageAndWindow, stageAndWindow)

    @InUi
    private fun updateWindowAndStage(scene: Window?, stage: Stage?) {
        currentStage = stage
        currentWindow = scene
    }

    /**
     * If we are a window / owning the context, we can close the "stage".
     */
    @InAny
    fun closeView() = inUi {
        if (mayClose) {
            onClosing()
            currentStage?.close()
        } else {
            L.debug(this::class, "tried to close when but was not allowed.")
        }
    }

    /**
     * Controls whenever a closeView is allowed.
     */
    @NoEscape //you should never "take this" out, as it can change.
    var mayClose: Boolean = true

    /**
     * Is this view embedded or is it "the toplevel / standalone view" ?
     */
    var isInline: Boolean = false
        private set

    /**
     * is this a standalone view.
     */
    val isStandalone: Boolean
        get() = !isInline

    //TODO experimental example
    fun executeInUIInOrder(vararg jobs: Job) = coroutineScope.launchMain {
        jobs.joinAll()
    }
}

