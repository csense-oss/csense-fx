package csense.javafx.views

import csense.javafx.tracking.BaseViewTrackingEvents
import csense.javafx.views.base.BaseViewController
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.BaseView
//
import csense.kotlin.FunctionUnit
import csense.kotlin.annotations.threading.*
import javafx.scene.Parent
import javafx.stage.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

/**
 * Conceptualize a view with input and output
 * typical either a view computing a result, resolving a result, from a given input.
 * like looking into a database (with an input of what name for example)
 */
abstract class BaseViewControllerInOutput<ViewBinding : BaseView<Parent>, Din, Dout>(
        private val input: Din
) : BaseViewControllerInOutputNoStart<ViewBinding, Din, Dout>(input) {
    
    @InUi
    override fun start() {
        super.start()
        inUi(input) {
            setupOnRender(isInline, binding)
            onStartData()
            tracker.logEvent(BaseViewTrackingEvents.Ready)
        }
    }
    
    @InUi
    protected abstract fun InUiUpdateInput<ViewBinding, Din>.onStartData()
}

/**
 *
 * @param ViewBinding : BaseView<Parent>
 * @param Din
 * @param DinTransformed
 * @param Dout
 * @constructor
 */
abstract class BaseViewControllerInOutputTransformed<ViewBinding : BaseView<Parent>, Din, DinTransformed, Dout>(
        input: Din
) : BaseViewControllerInOutputNoStart<ViewBinding, Din, Dout>(input),
        InputViewAble<Din, DinTransformed> {
    
    
    val inputDataLoader: Deferred<DinTransformed>
        get() = startDataFlowLoader.startDataFlow.result
    
    private val startDataFlowLoader by lazy {
        InputDataLoader(this,
                ::viewLoader,
                input,
                coroutineScope,
                ::transformInput)
    }
    
    override fun start() {
        super.start()
        startDataFlowLoader.start(onUILoaded = {
            setupOnRender(isInline, it)
        }, callback = {
            onStartDataTransformed()
            tracker.logEvent(BaseViewTrackingEvents.Ready)
        })
    }
    
    abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartDataTransformed()
}


abstract class BaseViewControllerInOutputNoStart<ViewBinding : BaseView<Parent>, Din, Dout>(
        private val input: Din
) : BaseViewController<ViewBinding>(),
        OutputViewAble<Dout>,
        OnViewBindingRenderType<ViewBinding> {
    
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
        createInternalNewWindow(window, configureStage, Modality.WINDOW_MODAL)
    }, computeAction = {
        input.join()
        waitForResultAsync()
    })
    
    private suspend fun waitForResultAsync(): Dout {
        return result.await()
    }
    
    private val result: CompletableDeferred<Dout> = CompletableDeferred()
}