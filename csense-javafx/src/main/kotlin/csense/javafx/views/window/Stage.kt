package csense.javafx.views.window

import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle

open class TransparentStage : Stage() {
    init {
        initStyle(StageStyle.TRANSPARENT)
    }

    fun setSceneTransparent(scene: Scene) {
        scene.stylesheets.clear()
        scene.fill = Color.TRANSPARENT
        setScene(scene)
    }
}