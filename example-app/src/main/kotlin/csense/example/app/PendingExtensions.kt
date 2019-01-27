package csense.example.app

import csense.javafx.views.BaseView
import csense.javafx.views.InUiUpdateInputScope
import csense.javafx.views.OutputViewAble
import javafx.scene.Parent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


fun <ViewBinding : Parent, ViewToShow, Dout> BaseView<*, ViewBinding>.presentView(
    viewToShow: ViewToShow,
    uiAction: InUiUpdateInputScope<ViewBinding, Dout?>
): Job where ViewToShow : BaseView<*, out Parent>, ViewToShow : OutputViewAble<Dout> =
    backgroundToUi(computeAction = {
        suspendCancellableCoroutine<Deferred<Dout?>> { continuation ->
            viewToShow.start()
            viewToShow.presentModal(null)
//            viewToShow.inUi {
//                val stage = Stage()
            viewToShow.inUi {
                currentStage?.setOnHiding {
                    continuation.resume(viewToShow.createResultAsync())
                }
            }
//                stage.scene = Scene(binding, 300.0, 300.0)
//                stage.show()
//            }
        }.await()
    }, uiAction = uiAction)
