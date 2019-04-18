package csense.example.app

import csense.javafx.BaseApplication
import csense.javafx.viewdsl.hBox
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.textField
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyView
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.javafx.widgets.Toast
import javafx.application.Application
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.stage.Stage
import kotlinx.coroutines.MainScope

fun main(args: Array<String>) {
    SystemScaling.fixTextScaling()
    Application.launch(FxApplicationWrapper::class.java, *args)
}

class FxApplicationWrapper : BaseApplication() {

    override val startingHeight: Double = 400.0

    override val startingWidth: Double = 500.0

    override fun createView(): BaseView<*, *> {
        return LoginViewController()
    }

    override fun createSplashScreen(): Parent {
        return Label("Loading")
    }
}

class LoginView(onViewSetup: OnViewSetup) : LoadViewAble<Parent>(onViewSetup) {
    val UsernameField: Node

    val UserPasswordField: Node

    override val root = vBox {
        label { text = "Enter %name" }
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

class LoginViewController : BaseEmptyView<Unit, LoginView>(
    { _: Unit, onViewSetup: OnViewSetup ->
        LoginView(onViewSetup)
    }
) {
    override fun InUiUpdateEmpty<LoginView>.onReady() {

        Toast.showQuickNotificationToast("")
        (binding.UserPasswordField.scene?.window as? Stage)?.close()
    }

    override suspend fun loadView(): Unit = Unit

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