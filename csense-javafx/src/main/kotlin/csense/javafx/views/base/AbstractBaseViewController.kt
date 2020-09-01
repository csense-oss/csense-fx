@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.views.base

import csense.javafx.extensions.*
import csense.javafx.extensions.parent.*
import csense.javafx.extensions.system.*
import csense.javafx.extensions.system.StageExtensions.stageWith
import csense.javafx.observable.*
import csense.javafx.tracking.*
import csense.javafx.views.*
import csense.kotlin.*
import csense.kotlin.annotations.inheritance.*
import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.annotations.threading.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.coroutines.*
import csense.kotlin.logger.*
import csense.kotlin.datastructures.values.*
import javafx.scene.*
import javafx.scene.layout.*
import javafx.stage.*
import kotlinx.coroutines.*
import kotlin.reflect.*


/**
 *
 * This Guy will contain all the necessary low level stuff;
 *
 * This includes the "bouncing" feature of "threading" / coroutines;
 * The point of Bouncing can be seen in the Bouncing.md file
 */
abstract class AbstractBaseViewController<ViewBinding : BaseView<Parent>> :
        ToUi<ViewBinding>,
        ToBackground<ViewBinding> {
    
    internal val tracker = BaseViewTracker().addObserverF {
        L.debugLazy(this::class, { it.logTimingString() }, null)
    }
    
    @InUi
    private var currentStage: Stage? = null
    
    @InUi
    private var currentWindow: Window? = null
    
    internal val coroutineScope = BaseViewCoroutineScopeImpl()
    
    internal abstract val viewLoader: Deferred<ViewBinding>
    
    //region inUi state transfer
    
    private inline fun <Input> createInUiUpdate(view: ViewBinding, input: Input) =
            InUiUpdateInput(
                    currentWindow,
                    currentStage,
                    view,
                    input,
                    this
            )
    
    private inline fun createInUiEmpty(view: ViewBinding) =
            InUiUpdateEmpty(currentWindow, currentStage, view, this)
    
    override fun inUi(@InUi action: InUiUpdateEmptyScope<ViewBinding>): Job = coroutineScope.launchMain {
        action(
                InUiUpdateEmpty(
                        currentWindow,
                        currentStage,
                        viewLoader.await(),
                        this@AbstractBaseViewController
                )
        )
    }
    
    final override fun <T> inUi(
            input: T,
            action: InUiUpdateInputScope<ViewBinding, T>
    ): Job = coroutineScope.launchMain {
        val view = viewLoader.await()
        action(createInUiUpdate(view, input))
    }
    
    
    final override fun <Output> inUiAsync(
            @InUi action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = viewLoader.await()
        action(createInUiEmpty(view))
    }
    
    final override fun <Input, Output> inUiAsync(
            input: Input,
            @InUi action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = viewLoader.await()
        action(createInUiUpdate(view, input))
    }
    
    final override fun <T, Output : Deferred<T>> inUiDeferredAsync(
            @InUi action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<T> = coroutineScope.asyncMain {
        val view = viewLoader.await()
        action(createInUiEmpty(view)).await()
    }
    
    override fun <Input, T, Output : Deferred<T>> inUiDeferredAsync(
            input: Input,
            action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<T> = coroutineScope.asyncMain {
        val view = viewLoader.await()
        action(createInUiUpdate(view, input)).await()
    }

//endregion
    
    
    //region OnBackground
    final override fun inBackground(
            @InBackground action: InBackgroundEmptyScope<ViewBinding>
    ): Job = coroutineScope.launchDefault {
        tryAndLog {
            action(InBackgroundEmpty(this@AbstractBaseViewController, this))
        }
    }
    
    final override fun <Input> inBackground(
            input: Input,
            @InBackground action: InBackgroundInputScope<ViewBinding, Input>
    ): Job = coroutineScope.launchDefault {
        action(InBackgroundInput(input, this@AbstractBaseViewController))
    }
    
    final override fun <Output> inBackgroundAsync(
            @InBackground action: InBackgroundOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundEmpty(this@AbstractBaseViewController, coroutineScope))
    }
    
    final override fun <Input, Output> inBackgroundAsync(
            input: Input,
            @InBackground action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundInput(input, this@AbstractBaseViewController))
    }
    
    override fun <T, Output : Deferred<T>> inBackgroundDeferredAsync(
            action: InBackgroundOutputScope<ViewBinding, Output>
    ): Deferred<T> = coroutineScope.asyncDefault {
        action(InBackgroundEmpty(this@AbstractBaseViewController, coroutineScope)).await()
    }
    
    override fun <Input, T, Output : Deferred<T>> inBackgroundDeferredAsync(
            input: Input,
            action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<T> = coroutineScope.asyncDefault {
        action(InBackgroundInput(input, this@AbstractBaseViewController)).await()
    }
    
    //endregion
    
    //region consider moving out ? eg extensions ?
    fun <Dout, T : OutputViewAble<out Dout>> getResultFromControllerAsync(
            @InUi getter: ReceiverFunction0<ViewBinding, T>
    ): Deferred<Dout> = inUiDeferredAsync {
        getter(binding).createResultAsync()
    }
    
    
    fun <T> getAllFromUiAsync(@InUi getter: ReceiverFunction0<ViewBinding, List<T>>): Deferred<List<T>> = inUiAsync {
        getter(binding)
    }
    
    fun <T> getSingleFromUiAsync(@InUi getter: ReceiverFunction0<ViewBinding, T>): Deferred<T> = inUiAsync {
        getter(binding)
    }
    //endregion
    
    
    open fun transitionTo(
            view: AbstractBaseViewController<*>,
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
            @InUi configureStage: FunctionUnit<Stage>? = null,
            modal: Modality? = null,
            stageStyle: StageStyle? = null
    ): Job {
        return inUi(window) {
            val bindingRoot = binding.root
            isInline = false
            val newStage = stageWith(bindingRoot, window, modal, stageStyle) {
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
    @SuperCallRequired
    internal open fun onClosing() {
        tracker.onClosing()
    }
    
    /**
     * When window /stage and view should be "ok".
     */
    @InUi
    @SuperCallRequired
    internal open fun start() {
        tracker.onStart()
    }
    
    open fun getInitialSize(): WidthHeight {
        return WidthHeight.UseComputed
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
    fun closeView(): Job = inUi {
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
    var isInline: Boolean by SetOnceBool(false)
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
