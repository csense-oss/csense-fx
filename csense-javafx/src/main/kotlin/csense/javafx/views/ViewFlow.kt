package csense.javafx.views

import csense.javafx.views.base.OnViewSetup
import csense.kotlin.AsyncFunction0
import csense.kotlin.AsyncFunction1
import csense.kotlin.annotations.threading.InAny
import csense.kotlin.annotations.threading.InBackground
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.coroutines.asyncDefault
import csense.kotlin.extensions.coroutines.asyncIO
import kotlinx.coroutines.*
import kotlin.reflect.KClass


open class InputDataFlow<Din, DinTransformed>(
        scope: CoroutineScope,
        input: Din,
        @InBackground private val transformInput: AsyncFunction1<Din, DinTransformed>
) {

    val result: Deferred<DinTransformed> =
            scope.asyncDefault { transformInput(input) }

}

abstract class ViewFlow<ViewLoad, ViewBinding>(scope: CoroutineScope) {

    val view: Deferred<ViewBinding> by lazy {
        scope.loadAndTransformMain({ loadView() }) {
            bindView(
                    it,
                    OnViewSetup.instance
            )
        }
    }


    @InBackground
    abstract suspend fun loadView(): ViewLoad

    @InUi
    abstract fun bindView(loadedView: ViewLoad, onViewSetup: OnViewSetup): ViewBinding
}

private fun <T, U> CoroutineScope.loadAndTransformDefault(
        @InBackground loader: suspend () -> T,
        @InBackground transformer: suspend (T) -> U
): Deferred<U> = loadAndTransform(Dispatchers.Default, loader, transformer)

private fun <T, U> CoroutineScope.loadAndTransformMain(
        @InBackground loader: suspend () -> T,
        @InUi transformer: suspend (T) -> U
): Deferred<U> = loadAndTransform(Dispatchers.Main, loader, transformer)


private fun <T, U> CoroutineScope.loadAndTransform(
        dispatcher: CoroutineDispatcher,
        @InBackground loader: suspend () -> T,
        transformer: suspend (T) -> U
): Deferred<U> = asyncIO {
    val loaded = loader()
    withContext(dispatcher) {
        transformer(loaded)
    }
}

class DelegatingViewFlow<ViewLoad, ViewBinding>(
        scope: CoroutineScope,
        @InBackground private val loadViewFunc: AsyncFunction0<ViewLoad>,
        @InUi private val bindFunction: Function2<ViewLoad, OnViewSetup, ViewBinding>
) : ViewFlow<ViewLoad, ViewBinding>(scope) {

    @InBackground
    override suspend fun loadView(): ViewLoad = loadViewFunc()

    @InUi
    override fun bindView(loadedView: ViewLoad, onViewSetup: OnViewSetup): ViewBinding =
            bindFunction(loadedView, onViewSetup)

}

