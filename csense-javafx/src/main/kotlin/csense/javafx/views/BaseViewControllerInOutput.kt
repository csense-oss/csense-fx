package csense.javafx.views

import csense.javafx.tracking.BaseViewTrackingEvents
import csense.javafx.views.base.BaseViewController
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.BaseView
//
import csense.kotlin.FunctionUnit
import csense.kotlin.annotations.threading.InAny
import csense.kotlin.annotations.threading.InUi
import javafx.scene.Parent
import javafx.stage.Stage
import javafx.stage.Window
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

/**
 * Conceptualize a view with input and output
 * typical either a view computing a result, resolving a result, from a given input.
 * like looking into a database (with an input of what name for example)
 */
abstract class BaseViewControllerInOutput<ViewBinding : BaseView<Parent>, Din, DinTransformed, Dout>(
        input: Din
) : BaseViewController<ViewBinding>(),
        OutputViewAble<Dout>,
        InputViewAble<Din, DinTransformed>,
        OnViewBindingRenderType<ViewBinding> {


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
            setupOnRender(isInline, it)
        }, callback = {
            onStartData()
            tracker.logEvent(BaseViewTrackingEvents.Ready)
        })
    }


    @InAny
    fun closeWithResult(): Job = inBackground {
        result.complete(createResultAsync().await())
        closeView().join()
    }

    @InAny
    fun presentThisAsModal(
            window: Window? = null,
            @InUi configureStage: FunctionUnit<Stage>? = null
    ): Deferred<Dout> = uiToBackgroundAsync(uiAction = {
        createInternalNewWindow(window, configureStage)
    }, computeAction = {
        input.join()
        waitForResultAsync()
    })

    private suspend fun waitForResultAsync(): Dout {
        return result.await()
    }

    private val result: CompletableDeferred<Dout> = CompletableDeferred()

    @InUi
    protected abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartData()
}
