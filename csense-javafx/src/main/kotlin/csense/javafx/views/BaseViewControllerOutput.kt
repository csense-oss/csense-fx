package csense.javafx.views

import csense.kotlin.annotations.threading.*
import csense.javafx.tracking.*
import csense.javafx.views.base.BaseViewController
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.BaseView
import csense.kotlin.*
import csense.kotlin.exceptions.NoStackTraceException
import javafx.scene.Parent
import javafx.stage.*
import kotlinx.coroutines.*


/**
 * Conceptualize a view with only output, so no input
 */
abstract class BaseViewControllerOutput<ViewBinding : BaseView<Parent>, Dout> :
        BaseViewController<ViewBinding>(),
        OutputViewAble<Dout>,
        OnViewBindingRenderType<ViewBinding> {

    @InUi
    override fun start() {
        super.start()
        inUi {
            setupOnRender(isInline, binding)
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
    ): Deferred<Dout> = uiToBackgroundAsync(uiAction = {
        createInternalNewWindow(window, configureStage)
    }, computeAction = {
        input.join()
        waitForResultAsync()
    })

    private suspend fun waitForResultAsync(): Dout {
        return result.await()
    }

    private val result: CompletableDeferred<Dout> = CompletableDeferred()

    @InAny
    fun closeWithResult(): Job = inBackground {
        result.complete(createResultAsync().await())
        closeView().join()
    }

    @InAny
    fun closeWithNoResult(): Job = inBackground {
        result.completeExceptionally(ClosedNoResultException)
        closeView().join()
    }
}

val ClosedNoResultException: Exception = NoStackTraceException("View closed with no result")

interface OutputViewAble<Dout> {
    @InBackground
    fun createResultAsync(): Deferred<Dout>
}
