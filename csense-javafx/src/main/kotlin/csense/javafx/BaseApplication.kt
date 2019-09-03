package csense.javafx

import csense.javafx.views.base.BaseView
import csense.kotlin.logger.L
import csense.kotlin.logger.debug
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.measureTimeMillis

abstract class BaseApplication : Application() {

    abstract val startingHeight: Double

    abstract val startingWidth: Double

    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) {
            return
        }
        val newScene = Scene(createSplashScreen(), startingWidth, startingHeight)
        val startSplashTime = measureTimeMillis {
            primaryStage.scene = newScene
            primaryStage.show()
        }

        val view = createView()
        view.addToScene(newScene)
        L.debug(this::class, "splash took $startSplashTime ms to show")
    }


    abstract fun createView(): BaseView<*, *>

    abstract fun createSplashScreen(): Parent

}
