@file:Suppress("unused")

package csense.javafx.views

import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent


/**
 * Base empty view, only calls on ready when the ui is available.
 */
abstract class BaseEmptyView<ViewLoad, ViewBinding : LoadViewAble<out Parent>>(
    viewLoader: Function2<ViewLoad, OnViewSetup, ViewBinding>
) : BaseView<ViewLoad, ViewBinding>(
    viewLoader
) {
    override fun start() {
        inUi { onReady() }
    }

    abstract fun InUiUpdateEmpty<ViewBinding>.onReady()
}

