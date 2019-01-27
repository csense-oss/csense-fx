package csense.javafx

import csense.javafx.extensions.launchMain
import csense.javafx.views.BaseView
import csense.javafx.views.OnViewSetup
import csense.javafx.views.data.BaseViewCoroutineScopeImpl
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.measureTimeMillis

abstract class BaseApplication : Application() {
    private val coroutineScope = BaseViewCoroutineScopeImpl()
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) {
            return
        }

        val startSplashTime = measureTimeMillis {
            val newScene = Scene(createSplashScreen())
            primaryStage.scene = newScene
            primaryStage.show()
        }

        val view = createView()
        coroutineScope.launchMain {
            val loaded = view.loadView(OnViewSetup.instance)

        }

    }

    abstract fun createView(): BaseView<*, *>

    abstract fun createSplashScreen(): Parent

}
