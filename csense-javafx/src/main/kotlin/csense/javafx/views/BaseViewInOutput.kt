package csense.javafx.views

import csense.javafx.tracking.BaseViewTrackingEvents
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.kotlin.FunctionUnit
import csense.kotlin.annotations.sideEffect.NoEscape
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
abstract class BaseViewInOutput<ViewLoad, ViewBinding : LoadViewAble<Parent>, Din, DinTransformed, Dout>(
        input: Din,
        @InUi viewLoader: Function2<@NoEscape ViewLoad, @NoEscape OnViewSetup, ViewBinding>
) : BaseView<ViewLoad, ViewBinding>(viewLoader),
        OutputViewAble<Dout>,
        InputViewAble<Din, DinTransformed>,
        OnViewBindingRenderType<ViewBinding> {


    val inputDataLoader: Deferred<DinTransformed>
        get() = startDataFlowLoader.startDataFlow.result

    private val startDataFlowLoader by lazy {
        InputDataLoader(this,
                ::preloadView,
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


abstract class SimpleBaseViewInOutput<ViewBinding : LoadViewAble<Parent>, Din, DinTransformed, Dout>(
        input: Din,
        viewConstructor: Function1<OnViewSetup, ViewBinding>
) : BaseViewInOutput<SimpleBaseViewInOutput<ViewBinding, Din, DinTransformed, Dout>, ViewBinding, Din, DinTransformed, Dout>(
        input,
        { simpleBaseViewInOutput: SimpleBaseViewInOutput<ViewBinding, Din, DinTransformed, Dout>, onViewSetup: OnViewSetup ->
            viewConstructor(onViewSetup)
        }
) {
    override suspend fun loadView(): SimpleBaseViewInOutput<ViewBinding, Din, DinTransformed, Dout> = this
}


abstract class SimpleBaseViewInOutputNoTransform<ViewBinding : LoadViewAble<Parent>, Din, Dout>(
        input: Din,
        viewConstructor: Function1<OnViewSetup, ViewBinding>
) : SimpleBaseViewInOutput<ViewBinding, Din, Din, Dout>(
        input,
        viewConstructor
) {
    override suspend fun transformInput(input: Din): Din = input
}