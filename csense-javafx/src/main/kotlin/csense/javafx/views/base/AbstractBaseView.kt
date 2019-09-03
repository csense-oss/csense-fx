package csense.javafx.views.base

import csense.javafx.extensions.StageExtensions.stageWith
import csense.javafx.extensions.parent.*
import csense.javafx.extensions.showFluent
import csense.javafx.views.*
import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.*
import csense.kotlin.extensions.coroutines.*
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
 *
 *
 * This includes the "bouncing" feature of "threading" / coroutines;
 * The point of Bouncing can be seen in the Bouncing.md file
 */
abstract class AbstractBaseView<ViewBinding : LoadViewAble<out Parent>> :
        ToUi<ViewBinding>,
        ToBackground<ViewBinding> {

    private var currentStage: Stage? = null
    private var currentWindow: Window? = null

    internal val coroutineScope = BaseViewCoroutineScopeImpl()

    internal abstract fun getViewAsync(): Deferred<ViewBinding>

    //region inUi state transfer
    override fun inUi(action: InUiUpdateEmptyScope<ViewBinding>): Job = coroutineScope.launchMain {
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
            action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<T> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateEmpty(currentWindow, currentStage, view, this@AbstractBaseView)).await()
    }

    final override fun <Output> inUiAsync(
            action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateEmpty(currentWindow, currentStage, view, this@AbstractBaseView))
    }

    final override fun <Input, Output> inUiAsync(
            input: Input,
            action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
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

    fun <T> getFromUiAsync(getter: Function1<ViewBinding, List<T>>): Deferred<List<T>> = inUiAsync {
        getter(binding)
    }

    fun <Dout, T : OutputViewAble<out Dout>> getFromUiControllerAsync(
            getter: Function1<ViewBinding, T>
    ): Deferred<Dout> = inUiDeferredAsync {
        getter(binding).createResultAsync()
    }

//    fun <Dout, T : BaseViewInOutput<*, *, *, *, Dout>> getFromUiController(getter: Function1<ViewBinding, T>): Deferred<T> {
//
//    }
    //endregion


    //region OnBackground
    final override fun inBackground(
            action: InBackgroundEmptyScope<ViewBinding>
    ): Job = coroutineScope.launchDefault {
        tryAndLog {
            action(InBackgroundEmpty(this@AbstractBaseView, this))
        }
    }

    final override fun <Input> inBackground(
            input: Input,
            action: InBackgroundInputScope<ViewBinding, Input>
    ): Job = coroutineScope.launchDefault {
        action(InBackgroundInput(input, this@AbstractBaseView))
    }

    final override fun <Output> inBackgroundAsync(
            action: InBackgroundOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundEmpty(this@AbstractBaseView, coroutineScope))
    }

    final override fun <Input, Output> inBackgroundAsync(
            input: Input,
            action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncDefault {
        action(InBackgroundInput(input, this@AbstractBaseView))
    }

    open fun presentThisAsModal(
            ownerWindow: Window? = null,
            configureStage: FunctionUnit<Stage>? = null
    ): Job = inUi(ownerWindow) {
        val newStage = stageWith(binding.root) {
            //continue the same place as we left off.
            ownerWindow?.let { ownerWindow ->
                it.x = ownerWindow.x
                it.y = ownerWindow.y
            }
            configureStage?.invoke(it)
        }.showFluent()
        updateWindowAndStage(newStage, newStage)
        start()
    }

    open fun transitionTo(
            view: AbstractBaseView<*>,
            configureStage: FunctionUnit<Stage>? = null
    ): Job = inUi() {
        view.presentThisAsModal(currentWindow, configureStage)
        inUi(currentStage) {
            this.input?.close()
        }
    }

    open fun addToView(toPlaceIn: Pane): Job = inUi(toPlaceIn) {
        input.addToFront(binding.root)
        updateWindowAndStage(
                input.scene?.window,
                input.scene?.window as? Stage
        )
        start()
    }

    open fun addToScene(toPlaceIn: Scene): Job = inUi(toPlaceIn) {
        input.root = binding.root
        updateWindowAndStage(input.window, input.window as? Stage)
        start()
    }

    /**
     *
     */
    internal open fun start() {
    }

    private fun updateWindowAndStage(scene: Window?, stage: Stage?) {
        currentStage = stage
        currentWindow = scene
    }

    /**
     * If we are a window / owning the context, we can close the "stage".
     */
    fun closeView() = inUi {
        if (isOwningWindowAndStage) {
            currentStage?.close()
        }
    }

    val isOwningWindowAndStage: Boolean
        get() = currentStage != null && currentWindow != null

    //TODO experimental example

    fun executeInUIInOrder(vararg jobs: Job) {
        coroutineScope.launchMain {
            jobs.joinAll()
        }
    }
}

//TODO csense ?
suspend fun Array<out Job>.joinAll() {
    forEach { it.join() }
}
