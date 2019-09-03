package csense.javafx.tools.fps

import csense.javafx.animations.*;
import com.sun.javafx.perf.PerformanceTracker
import csense.kotlin.logger.logClassWarning
import javafx.scene.Scene
import java.security.AccessControlException


/**
 *
 * @property tracker PerformanceTracker
 * @constructor
 */
class FpsCounter(scene: Scene) {
    /**
     *
     */
    private val tracker: PerformanceTracker = PerformanceTracker.getSceneTracker(scene)

    init {
        enableSystemWide()
    }

    private fun enableSystemWide() {
        try {
            System.setProperty("prism.verbose", "true")
            System.setProperty("prism.dirtyopts", "false")
            System.setProperty("javafx.animation.pulse", "10")
        } catch (e: AccessControlException) {
            logClassWarning("error enabling fps counting system wide", e)
        }
    }

    fun getAverageFps(): Float {
        val fps = tracker.averageFPS
        tracker.resetAverageFPS()
        return fps
    }
}

/**
 * Logs the fps counting to the console.
 * @receiver Stage
 * @return AnimationTimerForCallback
 */
fun Scene.logFps(): AnimationTimerForCallback {
    val counter = FpsCounter(this)
    return AnimationTimerForCallback {
        val avg = counter.getAverageFps()
        if (avg <= 0.1) {
            return@AnimationTimerForCallback
        }
        kotlin.io.println("Current fps is: $avg")
    }.apply {
        start()
    }
}