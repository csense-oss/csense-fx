@file:Suppress("unused")

package csense.javafx.views.base

import csense.javafx.views.*
import csense.kotlin.extensions.coroutines.*
import javafx.scene.*
import kotlinx.coroutines.*


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

    //TODO either keep or remove... hmm
    internal fun preloadView(): Deferred<ViewBinding> {
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

    fun <DataType, OutputType> backgroundToUiAsync(
            computeAction: InBackgroundOutputScope<ViewBinding, DataType>,
            uiAction: InUiUpdateInputOutputScope<ViewBinding, DataType, OutputType>
    ): Deferred<OutputType> = inBackgroundAsync {
        val backgroundData = computeAction(this)
        inUiAsync(backgroundData, uiAction).await()
    }


    fun <DataType> uiToBackground(
            retriveUiData: InUiUpdateOutputScope<ViewBinding, DataType>,
            backgroundAction: InBackgroundInputScope<ViewBinding, DataType>
    ): Job = inUi {
        val data = retriveUiData(this)
        inBackground(data, backgroundAction)
    }

    fun <DataType, OutputType> uiToBackgroundAsync(
            uiAction: InUiUpdateOutputScope<ViewBinding, DataType>,
            computeAction: InBackgroundInputOutputScope<ViewBinding, DataType, OutputType>
    ): Deferred<OutputType> = coroutineScope.asyncMain {
        val uiData = inUiAsync(uiAction).await()
        inBackgroundAsync(uiData, computeAction).await()
    }
}
