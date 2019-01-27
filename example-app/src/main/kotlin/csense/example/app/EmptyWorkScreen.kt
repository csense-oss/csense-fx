package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.views.BaseView
import csense.javafx.views.OnViewSetup
import javafx.scene.control.Button
import javafx.scene.layout.VBox


class EmptyWorkScreenView : VBox() {

    val showInputOutput: Button
    val showInput: Button
    val showOutput: Button

    init {
        showInputOutput = button("input output") {

        }
        showInput = button("in2") {

        }
//        vBox {
        showOutput = button("out") {

        }
//        }
    }
}


// NO IO
class EmptyWorkScreen : BaseView<EmptyWorkScreenView, EmptyWorkScreenView>() {
    override suspend fun loadView(onViewSetup: OnViewSetup): EmptyWorkScreenView {
        return EmptyWorkScreenView()
    }

    override fun bindView(loaded: EmptyWorkScreenView): EmptyWorkScreenView {
        loaded.showInputOutput.setOnAction {
            presentView(InOutWorkScreen("123")) {
                binding.showInputOutput.text = "= \"$input\"))"
            }
        }
        loaded.showInput.setOnAction {

            InputWorkScreen("").presentModal()
        }
        loaded.showOutput.setOnAction {
            presentView(OutputWorkScreen()) {
                binding.showOutput.text = "output (got \"$input\" back))"
            }
        }
        return loaded
    }
}


