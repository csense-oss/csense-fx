@file:Suppress("unused")

package csense.javafx.views

import csense.javafx.tracking.*
import csense.javafx.views.base.BaseViewController
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.BaseView
import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import javafx.scene.Parent
import javafx.stage.*
import kotlinx.coroutines.*


/**
 * Base empty view, only calls on ready when the ui is available.
 */
abstract class BaseEmptyViewController<ViewBinding : BaseView<Parent>>
    : BaseViewController<ViewBinding>() {
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
    
    @InAny
    fun presentThisAsModal(
            window: Window? = null,
            @InUi configureStage: FunctionUnit<Stage>? = null
    ): Job = uiToBackgroundAsync(uiAction = {
        createInternalNewWindow(window, configureStage, Modality.WINDOW_MODAL)
    }, computeAction = {
        input.join()
    })
}

