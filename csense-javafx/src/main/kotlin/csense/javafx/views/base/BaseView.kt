package csense.javafx.views.base

import csense.javafx.views.DelegatingViewFlow
import csense.javafx.views.ViewFlow
import csense.kotlin.logger.L
import csense.kotlin.logger.debug
import javafx.scene.Parent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


/**
 * Concepts the fact that there are no output nor input by this view.
 * akk a boring view.
 * Base for all base views.
 */
abstract class BaseView<out ViewLoad, ViewBinding : LoadViewAble<Parent>> constructor(
        private val viewLoader: Function2<ViewLoad, OnViewSetup, ViewBinding>
) : AbstractBaseView<ViewBinding>() {

    @Deprecated(message = "Inner workings", level = DeprecationLevel.HIDDEN)
    override fun getViewAsync(): Deferred<ViewBinding> {
        return viewBinding.view
    }

    private val viewBinding: ViewFlow<ViewLoad, ViewBinding> by lazy {
        DelegatingViewFlow(
                coroutineScope,
                ::loadView,
                viewLoader
        )
    }

    abstract suspend fun loadView(): ViewLoad

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
    ): Job = inUi {
        val data = retriveUiData(this)
        inBackground(data, backgroundAction)
    }
}
