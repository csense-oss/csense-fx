@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.extensions

import csense.kotlin.FunctionUnit
import csense.kotlin.annotations.threading.InUi
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.Window

object StageExtensions {
    /**
     * Convenience constructor for stages.
     * @param view Parent
     * @param configureStage FunctionUnit<Stage>?
     * @return Stage
     */
    @InUi
    inline fun stageWith(
            view: Parent,
            crossinline configureStage: FunctionUnit<Stage> = {}
    ): Stage = Stage().apply {
        scene = Scene(view)
        configure(configureStage)
    }
}

/**
 * Configures this stage with the given function iff not null.
 * @receiver Stage
 * @param configureStage FunctionUnit<Stage>?
 */
@InUi
inline fun Stage.configure(crossinline configureStage: FunctionUnit<Stage> = {}): Unit =
        configureStage(this)


/**
 * Shows this stage and returns this
 */
@InUi
inline fun Stage.showFluent(): Stage =
        apply { show() }

@InUi
inline fun Stage.updateXYFrom(window: Window) {
    this.x = window.x
    this.y = window.y
}

