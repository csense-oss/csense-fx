package csense.javafx.views

import csense.javafx.views.base.*
import csense.kotlin.AsyncFunction1
import csense.kotlin.logger.*
import javafx.scene.Parent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

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
        InputDataLoader(this, input, coroutineScope, ::transformInput)
    }

    override fun start() {
        startDataFlowLoader.start { onStartData() }
    }

    protected abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartData()

    abstract suspend fun transformInput(input: Din): DinTransformed
}

class InputDataLoader<Din, DinTransformed, ViewBinding : LoadViewAble<Parent>>(
        val onView: ToBackground<ViewBinding>,
        input: Din,
        coroutineScope: CoroutineScope,
        transformInput: AsyncFunction1<Din, DinTransformed>
) {

    val startDataFlow: InputDataFlow<Din, DinTransformed> = InputDataFlow(coroutineScope, input, transformInput)

    fun start(
            callback: InUiUpdateInputScope<ViewBinding, DinTransformed>
    ) {
        val started = System.currentTimeMillis()
        onView.inBackground {
            val startData = startDataFlow.result.await()
            inUi(startData, callback)
            val after = System.currentTimeMillis()
            logClassDebug("view took ${after - started}ms to load")
        }
    }
}
