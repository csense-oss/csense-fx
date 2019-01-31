package csense.example.app

//import csense.javafx.views.BaseView
import csense.javafx.BaseApplication
import csense.javafx.views.base.BaseView
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.control.Label

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
