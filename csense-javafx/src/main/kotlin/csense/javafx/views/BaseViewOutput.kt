package csense.javafx.views

import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent
import kotlinx.coroutines.Deferred


/**
 * Conceptualize a view with only output, so no input
 */
abstract class BaseViewOutput<ViewLoad, ViewBinding : LoadViewAble<out Parent>, Dout>(
    viewLoader: Function2<ViewLoad, OnViewSetup, ViewBinding>
) :
    BaseView<ViewLoad, ViewBinding>(viewLoader), OutputViewAble<Dout> {

    override fun start() {
        inUi { onReady() }
    }

    abstract fun InUiUpdateEmpty<ViewBinding>.onReady()
}


interface OutputViewAble<Dout> {
    fun createResultAsync(): Deferred<Dout>
}