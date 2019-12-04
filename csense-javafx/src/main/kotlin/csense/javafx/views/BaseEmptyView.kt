@file:Suppress("unused")

package csense.javafx.views

import csense.javafx.tracking.*
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.kotlin.annotations.sideEffect.NoEscape
import csense.kotlin.annotations.threading.InUi
import javafx.scene.Parent


/**
 * Base empty view, only calls on ready when the ui is available.
 */
abstract class BaseEmptyView<ViewLoad, ViewBinding : LoadViewAble<Parent>>(
        @InUi viewLoader: Function2<@NoEscape ViewLoad,@NoEscape OnViewSetup, ViewBinding>
) : BaseView<ViewLoad, ViewBinding>(
        viewLoader
) {
    @InUi
    override fun start() {
        super.start()
        inUi {
            onReady()
            tracker.logEvent(BaseViewTrackingEvents.Ready)
        }
    }

    @InUi
    abstract fun InUiUpdateEmpty<ViewBinding>.onReady()
}

