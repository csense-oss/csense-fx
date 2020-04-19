package csense.javafx

import csense.javafx.observable.addObserverF
import csense.javafx.tracking.*
import csense.javafx.views.base.BaseViewController
import csense.kotlin.annotations.numbers.DoubleLimitFromMin
import csense.kotlin.annotations.threading.InBackground
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.coroutines.asyncDefault
import csense.kotlin.extensions.coroutines.launchMain
import csense.kotlin.logger.L
import csense.kotlin.logger.logClassWarning
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.*

abstract class BaseApplication<BackgroundData> : Application() {

    private val coroutineScope = MainScope()

    private val applicationTracker = ApplicationTracker().addObserverF {
        val name = this::class.java.simpleName
        coroutineScope.launchMain {
            L.debug(name ?: "", it.logTimingString())
        }
    }


    private val backgroundDataLoader: Deferred<BackgroundData> = coroutineScope.asyncDefault {
        applicationTracker.onBackgroundLoadingStarted()
        val result = loadBackgroundData()
        applicationTracker.onBackgroundLoadingDone()
        result
    }


    @DoubleLimitFromMin(from = 0.0)
    abstract val startingHeight: Double

    @DoubleLimitFromMin(from = 0.0)
    abstract val startingWidth: Double

    @InUi
    override fun start(primaryStage: Stage?) {
        primaryStage ?: return
        
        val newScene = Scene(createSplashScreen(), startingWidth, startingHeight)
        primaryStage.scene = newScene
        primaryStage.show()
        applicationTracker.onCreatedSplash()

        coroutineScope.launchMain {
            applicationTracker.onLoadingController()
            
            val backgroundData = backgroundDataLoader.await()
            val view = createView(backgroundData)
            view.addToScene(newScene)
        }
        //TODO concept ?
//        view.addListenerSingleTime(Ready){
//            applicationTracker.onReady()
//        }
    }

    @InBackground
    abstract suspend fun loadBackgroundData(): BackgroundData

    @InUi
    abstract fun createView(data: BackgroundData): BaseViewController<*>

    @InUi
    abstract fun createSplashScreen(): Parent

    @InUi
    override fun stop() {
        coroutineScope.cancel()
        applicationTracker.onClosing()
        try {
            super.stop()
        } catch (e: Exception) {
            logClassWarning("failed at stopping application", e)
        }
    }
}

abstract class SimpleBaseApplication : BaseApplication<Unit>() {
    override suspend fun loadBackgroundData() = Unit

    override fun createView(data: Unit): BaseViewController<*> = createView()

    abstract fun createView(): BaseViewController<*>
}