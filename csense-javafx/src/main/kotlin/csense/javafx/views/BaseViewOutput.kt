package csense.javafx.views

import csense.kotlin.annotations.threading.*
import csense.javafx.tracking.*
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.kotlin.*
import csense.kotlin.annotations.sideEffect.NoEscape
import csense.kotlin.exceptions.NoStackTraceException
import javafx.scene.Parent
import javafx.stage.*
import kotlinx.coroutines.*
import kotlin.Function2


/**
 * Conceptualize a view with only output, so no input
 */
abstract class BaseViewOutput<ViewLoad, ViewBinding : LoadViewAble<Parent>, Dout>(
        @InUi viewLoader: Function2<@NoEscape ViewLoad, @NoEscape OnViewSetup, ViewBinding>
) :
        BaseView<ViewLoad, ViewBinding>(viewLoader),
        OutputViewAble<Dout>,
        OnViewBindingRenderType<ViewBinding> {

    @InAny
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

///**
// * Close an "outputable" view with the result (by calling CreateResultAsync)
// * @receiver T
// * @return Job
// */
//@AnyThread
//fun <T, Dout> T.closeWithResult(): Job
//        where T : BaseView<*, *>, T : OutputViewAble<Dout> = inBackground {
//    val result = createResultAsync().await()
//    //??? how to send the result back ?
//    closeView().join()
//}
//
///**
// * Close a "outputable" view with no result
// * @receiver T
// * @return Job
// */
//@AnyThread
////??? how to send the result back ?
//fun <T> T.CloseWithNoResult(): Job
//        where T : BaseView<*, *>, T : OutputViewAble<*> = closeView()