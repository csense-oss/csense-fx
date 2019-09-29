package csense.javafx.views

import csense.javafx.annotations.*
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent
import kotlinx.coroutines.*


/**
 * Conceptualize a view with only output, so no input
 */
abstract class BaseViewOutput<ViewLoad, ViewBinding : LoadViewAble<Parent>, Dout>(
        viewLoader: Function2<ViewLoad, OnViewSetup, ViewBinding>
) :
        BaseView<ViewLoad, ViewBinding>(viewLoader),
        OutputViewAble<Dout>,
        OnViewBindingRenderType<ViewBinding> {
    override fun start() {
        inUi {
            setupOnRender(isInline, binding)
            onReady()
        }
    }

    abstract fun InUiUpdateEmpty<ViewBinding>.onReady()
}

interface OutputViewAble<Dout> {
    @AnyThread
    fun createResultAsync(): Deferred<Dout>
}

/**
 * Close an "outputable" view with the result (by calling CreateResultAsync)
 * @receiver T
 * @return Job
 */
@AnyThread
fun <T, Dout> T.closeWithResult(): Job
        where T : BaseView<*, *>, T : OutputViewAble<Dout> = inBackground {
    val result = createResultAsync().await()
    //??? how to send the result back ?
    closeView().join()
}

/**
 * Close a "outputable" view with no result
 * @receiver T
 * @return Job
 */
@AnyThread
//??? how to send the result back ?
fun <T> T.CloseWithNoResult(): Job
        where T : BaseView<*, *>, T : OutputViewAble<*> = closeView()