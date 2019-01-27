@file:Suppress("unused")

package csense.javafx.views

import csense.javafx.views.base.AbstractBaseView
import csense.javafx.views.data.*
import javafx.scene.Parent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

//region Type aliases for the scoping (DSL) like state transistions / threading

//region InUI Scopes
typealias InUiUpdateEmptyScope<ViewBinding> = (@InScope InUiUpdateEmpty<ViewBinding>).() -> Unit

typealias InUiUpdateInputScope<ViewBinding, Input> = (@InScope InUiUpdateInput<ViewBinding, Input>).() -> Unit

typealias InUiUpdateInputOutputScope<ViewBinding, Input, Output> = (@InScope InUiUpdateInput<ViewBinding, Input>).() -> Output

typealias InUiUpdateOutputScope<ViewBinding, Output> = (@InScope InUiUpdateEmpty<ViewBinding>).() -> Output
//endregion

//region OnBackground scopes
typealias InBackgroundEmptyScope<ViewBinding> = suspend (@InScope InBackgroundEmpty<ViewBinding>).() -> Unit

typealias InBackgroundInputScope<ViewBinding, Input> = suspend (@InScope InBackgroundInput<ViewBinding, Input>).() -> Unit
typealias InBackgroundOutputScope<ViewBinding, Output> = suspend (@InScope InBackgroundEmpty<ViewBinding>).() -> Output
typealias InBackgroundInputOutputScope<ViewBinding, Input, Output> = suspend (@InScope InBackgroundInput<ViewBinding, Input>).() -> Output
//endregion


//endregion

/**
 * Concepts the fact that there are no output nor input by this view.
 * akk a borring view.
 *
 */
abstract class BaseView<ViewLoad, ViewBinding : Parent> : AbstractBaseView<ViewBinding>() {

    abstract fun bindView(loaded: ViewLoad): ViewBinding

    @Deprecated(message = "Inner workings", level = DeprecationLevel.HIDDEN)
    override fun getViewAsync(): Deferred<ViewBinding> {
        println("get view async")
        return viewBinding.view
    }

    private val viewBinding: ViewFlow<ViewLoad, ViewBinding> by lazy {
        DelegatingViewFlow(
            coroutineScope,
            ::loadView,
            ::bindView
        )
    }

    abstract suspend fun loadView(onViewSetup: OnViewSetup): ViewLoad

    fun <DataType> backgroundToUi(
        computeAction: InBackgroundOutputScope<ViewBinding, DataType>,
        uiAction: InUiUpdateInputScope<ViewBinding, DataType>
    ): Job = inBackground {
        val result = computeAction()
        inUi(result, uiAction)
    }


    fun <DataType> uiToBackground(
        retriveUiData: InUiUpdateOutputScope<ViewBinding, DataType>,
        backgroundAction: InBackgroundInputScope<ViewBinding, DataType>
    ) = inUi {
        val data = retriveUiData(this)
        inBackground(data, backgroundAction)
    }
}

/**
 * Tagging class, used to make it impossible for implementations to "recreate" the UI;
 */
class OnViewSetup private constructor() {
    companion object {
        internal val instance = OnViewSetup()
    }
}