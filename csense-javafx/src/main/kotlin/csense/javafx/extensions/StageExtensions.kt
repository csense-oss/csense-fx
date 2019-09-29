package csense.javafx.extensions

import csense.javafx.annotations.*
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.*
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.also

object StageExtensions {
    /**
     * Convenience constructor for stages.
     * @param view Parent
     * @param configureStage FunctionUnit<Stage>?
     * @return Stage
     */
    @InUI
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
@InUI
fun Stage.configure(configureStage: FunctionUnit<Stage>? = null): Unit =
    runIfNotNull(configureStage) ?: Unit


/**
 * Shows this stage and returns this
 */
@InUI
fun Stage.showFluent(): Stage = also { it.show() }