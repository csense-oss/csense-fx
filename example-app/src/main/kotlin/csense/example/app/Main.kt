package csense.example.app

import csense.javafx.views.BaseView
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

fun main(args: Array<String>) {
    Application.launch(FxApplicationWrapper::class.java, *args)
}

class FxApplicationWrapper() : Application() {
    override fun stop() {
        super.stop()
    }

    override fun init() {
        super.init()
    }

    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) {
            return
        }
        FxStage(primaryStage, EmptyWorkScreen(), Label("Loading"))
    }

}


class FxStage(
    stage: Stage,
    view: BaseView<*, out Parent>,
    loadingView: Parent?
) {
    init {
        stage.setOnHiding {
            println("will close..")
        }

        if (loadingView != null) {
            stage.scene = Scene(loadingView, 500.0, 800.0)
            stage.show()
        }
        view.inUi {
            if (loadingView == null) {
                stage.scene = Scene(binding, 300.0, 400.0)
                stage.show()
            } else {
                stage.scene?.root = binding
            }

        }

    }
}
