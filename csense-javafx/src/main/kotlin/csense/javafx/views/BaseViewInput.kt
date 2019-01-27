package csense.javafx.views

import csense.javafx.views.data.InUiUpdateInput
import csense.javafx.views.data.ToBackground
import csense.kotlin.AsyncFunction1
import javafx.scene.Parent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * Conceptualize a view with only input, no output
 * typical a view  / readonly view
 */
abstract class BaseViewInput<ViewLoad, ViewBinding : Parent, Din, DinTransformed>(
    private val input: Din
) :
    BaseView<ViewLoad, ViewBinding>() {

    val inputDataLoader: Deferred<DinTransformed>
        get () = startDataFlowLoader.startDataFlow.result

    private val startDataFlowLoader by lazy {
        InputDataLoader(this, input, coroutineScope, ::transformInput)
    }

    override fun start() {
        startDataFlowLoader.start { onStartData() }
    }

    protected abstract fun InUiUpdateInput<ViewBinding, DinTransformed>.onStartData()

    abstract suspend fun transformInput(input: Din): DinTransformed


}

class DelegatedInputDataFlow<Din, DinTransformed>(
    input: Din,
    scope: CoroutineScope,
    private val transformFunction: AsyncFunction1<Din, DinTransformed>
) :
    InputDataFlow<Din, DinTransformed>(scope, input) {
    override suspend fun transformInput(input: Din): DinTransformed = transformFunction(input)
}

class InputDataLoader<Din, DinTransformed, ViewBinding : Parent>(
    val onView: ToBackground<ViewBinding>,
    input: Din,
    coroutineScope: CoroutineScope,
    transformInput: AsyncFunction1<Din, DinTransformed>
) {
    val startDataFlow: InputDataFlow<Din, DinTransformed> =
        DelegatedInputDataFlow(input, coroutineScope) { transformInput(it) }


    fun start(
        callback: InUiUpdateInputScope<ViewBinding, DinTransformed>
    ) {
        println("got start $this, view: $onView")
        onView.inBackground {
            println("got background $this")
            val startData = startDataFlow.result.await()
            println("have transformed")
            inUi(startData, callback)
        }
    }
}