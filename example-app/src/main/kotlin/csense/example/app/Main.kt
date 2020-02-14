package csense.example.app

import csense.javafx.SimpleBaseApplication
import csense.javafx.extensions.parent.plusAssign
import csense.javafx.material.widgets.spinner.MatSpinnerSingleColor
import csense.javafx.viewdsl.hBox
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.textField
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.BaseViewController
import csense.javafx.views.base.BaseViewParent
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.list.SimpleListView
import csense.javafx.views.list.SimpleListViewAdapter
import csense.javafx.views.list.SimpleListViewRender
import csense.kotlin.extensions.coroutines.asyncMain
import csense.kotlin.logger.L
import csense.kotlin.logger.usePrintAsLoggers
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

fun main(args: Array<String>) {
    L.usePrintAsLoggers()
    SystemScaling.fixTextScaling()
    Application.launch(FxApplicationWrapper::class.java, *args)
}

class FxApplicationWrapper : SimpleBaseApplication() {

    override val startingHeight: Double = 400.0

    override val startingWidth: Double = 500.0

    override fun createSplashScreen(): Parent {
        return vBox {
            this += MatSpinnerSingleColor()
            padding = Insets(50.0)
            label {
                text = "Loading"
            }
        }
    }

    override suspend fun loadBackgroundData() {
        delay(50)
        super.loadBackgroundData()
    }

    override fun createView(): BaseViewController<*> {
        //        return FXMLWorkScreen()
//        return LoginViewController()
        return SimpleListExample()
    }
}

class LoginView : BaseView<Parent> {
    val UsernameField: Node

    val UserPasswordField: Node

    override val root = vBox {
        label { text = "Enter name" }
        UsernameField = textField {}
        label { text = "Enter password" }
        UserPasswordField = textField {}
        hBox {
            label { text = "hor 1" }
            label { text = "hor 2" }
            label { text = "hor 3" }
        }
    }
}

class LoginViewController : BaseEmptyViewController<LoginView>(
) {
    override fun InUiUpdateEmpty<LoginView>.onReady() {

//        Toast.showQuickNotificationToast("")
//        (binding.UserPasswordField.scene?.window as? Stage)?.close()
    }

    //    override suspend fun loadView(): Unit = Unit
    override fun CoroutineScope.createNewUi(): Deferred<LoginView> = asyncMain {
        LoginView()
    }

}

/**
 *
 */
object SystemScaling {
    private var haveFixed = false

    /**
     * Fixes windows text scaling issues.
     */
    fun fixTextScaling() {
        if (haveFixed) {
            return
        }
        haveFixed = true
        System.setProperty("prism.lcdtext", "false")
    }
}