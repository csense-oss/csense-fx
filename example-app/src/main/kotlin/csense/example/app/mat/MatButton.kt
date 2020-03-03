package csense.example.app.mat

import csense.javafx.extensions.parent.addToFront
import csense.javafx.material.widgets.button.MatButton
import csense.javafx.material.widgets.button.matButton
import csense.javafx.material.widgets.ripple.RippleControl
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseViewParent
import csense.javafx.views.base.InUiUpdateEmpty
import csense.kotlin.extensions.coroutines.asyncDefault
import javafx.geometry.Insets
import javafx.scene.Parent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

class MatButtonUi : BaseViewParent {
    val btn: MatButton
    val text = label {
        text = "omg"
        prefHeight = 200.0
        prefWidth = 200.0

    }
    val rippler = RippleControl(text).apply {
        maskType =  RippleControl.RipplerMask.CIRCLE
    }
    override val root: Parent = vBox {
        btn = matButton {
            padding = Insets(20.0)
            text = "myButton"
            prefWidth = 500.0
            prefHeight = 50.0
        }

        addToFront(rippler)
    }

}

class MatButtonController : BaseEmptyViewController<MatButtonUi>() {
    override fun CoroutineScope.createNewUi(): Deferred<MatButtonUi> = asyncDefault {
        MatButtonUi()
    }

    override fun InUiUpdateEmpty<MatButtonUi>.onReady() {
//        binding.btn.setOnClickAsyncEmpty {
//            Toast.showQuickNotificationToast("yaa")
//        }
    }


}