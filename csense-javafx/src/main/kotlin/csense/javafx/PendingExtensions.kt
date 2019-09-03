package csense.javafx

import csense.javafx.views.OutputViewAble
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateInputScope
import csense.javafx.views.base.LoadViewAble
import csense.kotlin.FunctionUnit
import javafx.scene.Parent
import javafx.stage.Stage
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

//
//fun <ViewBinding : LoadViewAble<out Parent>,
//        ViewToShow,
//        Dout> BaseView<*, ViewBinding>.presentView(
//    viewToShow: ViewToShow,
//    configureStage: FunctionUnit<Stage>? = null,
//    uiAction: InUiUpdateInputScope<ViewBinding, Dout?>
//): Job where ViewToShow : BaseView<*, out LoadViewAble<out Parent>>, ViewToShow : OutputViewAble<Dout> =
//    backgroundToUi(computeAction = {
//        suspendCancellableCoroutine<Deferred<Dout?>> { continuation ->
//            viewToShow.start()
//            viewToShow.presentThisAsModal(null, configureStage)
//            viewToShow.inUi {
//                currentStage?.setOnHiding {
//                    continuation.resume(viewToShow.createResultAsync())
//                }
//            }
//        }.await()
//    }, uiAction = uiAction)
