package csense.javafx.extensions

import csense.kotlin.FunctionUnit
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

object StageExtensions {
    /**
     * Conveince constructor for stages.
     * @param view Parent
     * @param configureStage FunctionUnit<Stage>?
     * @return Stage
     */
    fun stageWith(
        view: Parent,
        configureStage: FunctionUnit<Stage>? = null
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
fun Stage.configure(configureStage: FunctionUnit<Stage>? = null) {
    if (configureStage != null) {//todo use runIfNotNull instead
        run(configureStage)
    }
}

/**
 * Shows this stage and returns this
 */
fun Stage.showFluent(): Stage = apply { show() }//todo use also from csense