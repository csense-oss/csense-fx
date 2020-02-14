package csense.javafx.views

import csense.kotlin.annotations.threading.*
import csense.kotlin.annotations.threading.InUi
import csense.javafx.tracking.BaseViewTrackingEvents
import csense.javafx.views.base.*
import csense.kotlin.AsyncFunction1
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.coroutines.launchMain
import javafx.scene.Parent
import javafx.stage.Stage
import javafx.stage.Window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

/**
 * Conceptualize a view with only input, no output
 * typical a view  / readonly view
 */
abstract class BaseViewControllerInput<ViewBinding : BaseView<Parent>, Din, DinTransformed>(
        private val input: Din
) : BaseViewController<ViewBinding>(), InputViewAble<Din, DinTransformed> {

    val inputDataLoader: Deferred<DinTransformed>
        get() = startDataFlowLoader.startDataFlow.result

    private val startDataFlowLoader by lazy {
        InputDataLoader(this,
                ::viewLoader,
                input,
                coroutineScope,
                ::transformInput)
    }

    @InUi
    override fun start() {
        super.start()
        startDataFlowLoader.start(onUILoaded = {
            it.onUiReady()
        }, callback = {
            onStartData()
            tracker.logEvent(BaseViewTrackingEvents.Ready)
        })
    }

    @InUi
    internal open fun ViewBinding.onUiReady() {

    }

    @InUi
    protected abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartData()

    @InAny
    fun presentThisAsModal(
            window: Window? = null,
            @InUi configureStage: FunctionUnit<Stage>? = null
    ): Job = uiToBackgroundAsync(uiAction = {
        createInternalNewWindow(window, configureStage)
    }, computeAction = {
        input.join()
    })


}

class InputDataLoader<Din, DinTransformed, ViewBinding : BaseView<Parent>>(
        val onView: ToBackground<ViewBinding>,
        @InUi val preloadViewFunction: () -> Deferred<ViewBinding>,
        input: Din,
        coroutineScope: CoroutineScope,
        @InBackground transformInput: AsyncFunction1<Din, DinTransformed>
) {

    val startDataFlow: InputDataFlow<Din, DinTransformed> =
            InputDataFlow(coroutineScope, input, transformInput)

    @InUi
    fun start(
            @InUi onUILoaded: Function1<ViewBinding, Unit>,
            @InUi callback: InUiUpdateInputScope<ViewBinding, DinTransformed>
    ) {
        //start async to load view.
        val viewAsync = preloadViewFunction()
        //in background load transformation
        onView.inBackground {
            val backgroundDataAsync = startDataFlow.result
            backgroundDataAsync.start()
            val viewBinding = viewAsync.await()
            scope.launchMain { onUILoaded(viewBinding) }.join() //TODO consider "InUi" ?hm
            val startData = backgroundDataAsync.await()
            //wait for UI.
            //callback.
            inUi(startData, callback)
        }
    }
}


interface InputViewAble<Din, DinTransformed> {
    @InBackground
    suspend fun transformInput(input: Din): DinTransformed
}
