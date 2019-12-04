package csense.javafx

import csense.javafx.observable.addObserverF
import csense.javafx.tracking.*
import csense.javafx.views.base.BaseView
import csense.kotlin.annotations.numbers.DoubleLimit
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.logger.L
import csense.kotlin.logger.debug
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

abstract class BaseApplication : Application() {

    private val applicationTracker = ApplicationTracker().addObserverF {
        L.debug(this::class, it.logTimingString())
    }

    //    @DoubleLimit(from = 0, to = Double.MAX_VALUE)
    abstract val startingHeight: Double

    //    @DoubleLimit(from = 0, to = Double.MAX_VALUE)
    abstract val startingWidth: Double

    @InUi
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) {
            return
        }
        val newScene = Scene(createSplashScreen(), startingWidth, startingHeight)
        primaryStage.scene = newScene
        primaryStage.show()
        applicationTracker.onCreatedSplash()

        val view = createView()
        view.addToScene(newScene)
        applicationTracker.onLoadingController()
        //TODO concept ?
//        view.addListenerSingleTime(Ready){
//            applicationTracker.onReady()
//        }
    }


    @InUi
    abstract fun createView(): BaseView<*, *>

    @InUi
    abstract fun createSplashScreen(): Parent

    @InUi
    override fun stop() {
        applicationTracker.onClosing()
        super.stop()
    }
}
