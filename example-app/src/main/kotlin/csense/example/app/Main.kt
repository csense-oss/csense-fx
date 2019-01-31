package csense.example.app

//import csense.javafx.views.BaseView
import csense.javafx.BaseApplication
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.LoadViewAble
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.stage.Stage

fun main(args: Array<String>) {
    Application.launch(FxApplicationWrapper::class.java, *args)
}

class FxApplicationWrapper : BaseApplication() {
    override fun createView(): BaseView<*, *> {
        return EmptyWorkScreen()
    }

    override fun createSplashScreen(): Parent {
        return Label("Loading")
    }
}
