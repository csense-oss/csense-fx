package csense.javafx.widgets

import csense.javafx.animations.animate
import csense.kotlin.annotations.threading.*
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.stackPane
import csense.javafx.views.background.background
import csense.javafx.views.window.TransparentScene
import csense.javafx.views.window.TransparentStage
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.*
import kotlinx.coroutines.*


object Toast {
    @InUi
    fun showQuickNotificationToast(text: String, initialDelayInMs: Long = 3500): Job {
//        val plan = UIExecutionPlanBuilder()
//        plan.add {
        val stage = TransparentStage()

        val view = stackPane {

            label {
                this.text = text
                textFill = Color.WHITE
                font = Font.font(20.0)
            }
        }
        view.padding = Insets(45.0, 55.0, 45.0, 55.0)
        view.background = createBackground(15.0)
        stage.isAlwaysOnTop = true
        val scene = TransparentScene(view)
        stage.setSceneTransparent(scene)
        stage.show()
        return view.animate {
            delay(initialDelayInMs)
            GlobalScope.launch(Dispatchers.Main) {
                delay(500)
                stage.close()
            }
            while (it.opacity > 0.0) {
                it.opacity -= 0.01
                delay(2)
            }
        }
    }

    @InUi
    fun showText(primaryStage: Stage, text: String): Job {
        val stage = TransparentStage()

        val view = stackPane {

            label {
                this.text = text
                textFill = Color.WHITE
                font = Font.font(20.0)
            }
        }
        view.padding = Insets(45.0, 55.0, 45.0, 55.0)
        view.background = createBackground(15.0)
        stage.isAlwaysOnTop = true
        val scene = TransparentScene(view)
        stage.setSceneTransparent(scene)
        stage.show()
        return view.animate {
            delay(3500)
            GlobalScope.launch(Dispatchers.Main) {
                delay(500)
                stage.close()
            }
            while (it.opacity > 0.0) {
                it.opacity -= 0.01
                delay(2)
            }
        }
    }
}

@InUi
fun createBackground(radiiDouble: Double): Background {
    return BackgroundFill(
            Color.rgb(0x55, 0x55, 0x55, 0.8),
            CornerRadii(radiiDouble),
            Insets.EMPTY
    ).background()

}
