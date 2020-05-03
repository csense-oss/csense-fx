@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.extensions.system

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import javafx.scene.*
import javafx.stage.*

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
            owner: Window? = null,
            modal: Modality? = null,
            style: StageStyle? = null,
            crossinline configureStage: FunctionUnit<Stage> = {}
    ): Stage = Stage().apply {
        modal?.let(this::initModality)
        owner?.let(this::initOwner)
        style?.let(this::initStyle)
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
inline fun Stage.showFluent(): Stage = apply {
    show()
}

@InUi
inline fun Stage.showFluent(modal: Boolean): Stage {
    return if (modal) {
        showAndWaitFluent()
    } else {
        showFluent()
    }
}

@InUi
/**
 * Shows this Stage and returns this, after waiting for it to close.
 */
inline fun Stage.showAndWaitFluent(): Stage = apply {
    showAndWait()
}

@InUi
inline fun Stage.updateXYFrom(window: Window) {
    this.x = window.x
    this.y = window.y
}
