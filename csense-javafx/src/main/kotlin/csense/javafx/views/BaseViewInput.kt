package csense.javafx.views

import csense.javafx.annotations.*
import csense.javafx.views.base.*
import csense.kotlin.*
import csense.kotlin.logger.*
import javafx.scene.Parent
import kotlinx.coroutines.*

/**
 * Conceptualize a view with only input, no output
 * typical a view  / readonly view
 */
abstract class BaseViewInput<out ViewLoad, ViewBinding : LoadViewAble<Parent>, Din, DinTransformed>(
        private val input: Din,
        viewLoader: (ViewLoad, OnViewSetup) -> ViewBinding
) :
        BaseView<ViewLoad, ViewBinding>(viewLoader) {

    val inputDataLoader: Deferred<DinTransformed>
        get() = startDataFlowLoader.startDataFlow.result

    private val startDataFlowLoader by lazy {
        InputDataLoader(this,
                ::preloadView,
                input,
                coroutineScope,
                ::transformInput)
    }

    override fun start() {
        startDataFlowLoader.start({
            onStartData()
        }, {
            it.onUiReady()
        })
    }

    internal open fun ViewBinding.onUiReady() {

    }

    protected abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartData()

    abstract suspend fun transformInput(input: Din): DinTransformed
}

class InputDataLoader<Din, DinTransformed, ViewBinding : LoadViewAble<Parent>>(
        val onView: ToBackground<ViewBinding>,
        val preloadViewFunction: () -> Deferred<ViewBinding>,
        input: Din,
        coroutineScope: CoroutineScope,
        transformInput: AsyncFunction1<Din, DinTransformed>
) {

    val startDataFlow: InputDataFlow<Din, DinTransformed> = InputDataFlow(coroutineScope, input, transformInput)

    @InUI
    fun start(
            callback: InUiUpdateInputScope<ViewBinding, DinTransformed>,
            onUILoaded: AsyncFunction1<ViewBinding, Unit>
    ) {
        val started = System.currentTimeMillis()
        //start async to load view.
        val viewAsync = preloadViewFunction()
        //in background load transformation
        onView.inBackground {
            val backgroundDataAsync = startDataFlow.result
            backgroundDataAsync.start()
            onUILoaded(viewAsync.await())
            val startData = backgroundDataAsync.await()
            //wait for UI.
            //callback.
            inUi(startData, callback)
            val after = System.currentTimeMillis()
            logClassDebug("view took ${after - started} ms to load")
        }
    }
}
