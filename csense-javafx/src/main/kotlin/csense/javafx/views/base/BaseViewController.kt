@file:Suppress("unused")

package csense.javafx.views.base

import csense.kotlin.annotations.threading.InAny
import csense.kotlin.annotations.threading.InBackground
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.coroutines.*
import javafx.scene.Parent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


/**
 * Concepts the fact that there are no output nor input by this view.
 * akk a boring view.
 * Base for all base views.
 */
abstract class BaseViewController<ViewBinding : BaseView<Parent>>
    : AbstractBaseViewController<ViewBinding>() {
    
    override val viewLoader: Deferred<ViewBinding> by lazy {
        coroutineScope.createNewUi()
    }
    
    @InAny
    abstract fun CoroutineScope.createNewUi(): Deferred<ViewBinding>
    
    fun <DataType> backgroundToUi(
            @InBackground computeAction: InBackgroundOutputScope<ViewBinding, DataType>,
            @InUi uiAction: InUiUpdateInputScope<ViewBinding, DataType>
    ): Job = inBackground {
        val result = computeAction()
        inUi(result, uiAction)
    }
    
    fun <DataType, OutputType> backgroundToUiAsync(
            @InBackground computeAction: InBackgroundOutputScope<ViewBinding, DataType>,
            @InUi uiAction: InUiUpdateInputOutputScope<ViewBinding, DataType, OutputType>
    ): Deferred<OutputType> = inBackgroundAsync {
        val backgroundData = computeAction(this)
        inUiAsync(backgroundData, uiAction).await()
    }
    
    fun <DataType, DeferredData : Deferred<DataType>, OutputType> backgroundDeferredToUiAsync(
            @InBackground computeAction: InBackgroundOutputScope<ViewBinding, DeferredData>,
            @InUi uiAction: InUiUpdateInputOutputScope<ViewBinding, DataType, OutputType>
    ): Deferred<OutputType> = inBackgroundAsync {
        val backgroundData = computeAction(this).await()
        inUiAsync(backgroundData, uiAction).await()
    }
    
    
    fun <DataType> uiToBackground(
            @InUi retriveUiData: InUiUpdateOutputScope<ViewBinding, DataType>,
            @InBackground backgroundAction: InBackgroundInputScope<ViewBinding, DataType>
    ): Job = inUi {
        val data = retriveUiData(this)
        inBackground(data, backgroundAction)
    }
    
    fun <DataType, OutputType> uiToBackgroundAsync(
            @InUi uiAction: InUiUpdateOutputScope<ViewBinding, DataType>,
            @InBackground computeAction: InBackgroundInputOutputScope<ViewBinding, DataType, OutputType>
    ): Deferred<OutputType> = coroutineScope.asyncMain {
        val uiData = inUiAsync(uiAction).await()
        inBackgroundAsync(uiData, computeAction).await()
    }
    
    fun <UIData, ConvertedData> uiToBackgroundToUi(
            @InUi retriveUiData: InUiUpdateOutputScope<ViewBinding, UIData>,
            @InBackground backgroundAction: InBackgroundInputOutputScope<ViewBinding, UIData, ConvertedData>,
            @InUi postToUi: InUiUpdateInputScope<ViewBinding, ConvertedData>
    ): Job = coroutineScope.launchMain {
        val retriveUi = inUiAsync(retriveUiData).await()
        inBackground(retriveUi) {
            inUi(backgroundAction(this)) {
                postToUi(this)
            }.join()
        }.join()
    }
}