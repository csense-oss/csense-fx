package csense.javafx

import csense.javafx.views.base.BaseView
import csense.javafx.views.base.BaseViewCoroutineScopeImpl
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.measureTimeMillis

abstract class BaseApplication : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) {
            return
        }
        val newScene = Scene(createSplashScreen())
        val startSplashTime = measureTimeMillis {
            primaryStage.scene = newScene
            primaryStage.show()
        }

        val view = createView()
        view.addToScene(newScene)

    }

    abstract fun createView(): BaseView<*, *>

    abstract fun createSplashScreen(): Parent

}
