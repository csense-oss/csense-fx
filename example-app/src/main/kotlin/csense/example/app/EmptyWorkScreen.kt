package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.BaseView
import csense.kotlin.extensions.coroutines.asyncMain
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import kotlinx.coroutines.CoroutineScope


class EmptyWorkScreenView : BaseView<VBox> {

    val showInputOutput: Button
    val showInput: Button
    val showOutput: Button

    override val root: VBox = vBox {
        showInputOutput = button("input output") {

        }
        showInput = button("in2") {

        }
//        vBox {
        showOutput = button("out") {

        }
    }
}


// NO IO
class EmptyWorkScreen : BaseEmptyViewController<EmptyWorkScreenView>(
//    { _: Unit, onViewSetup: OnViewSetup -> EmptyWorkScreenView(onViewSetup) }
) {


//    override suspend fun loadView(): Unit = Unit

    override fun InUiUpdateEmpty<EmptyWorkScreenView>.onReady() {
        binding.showInputOutput.setOnAction {
            InOutWorkScreen("123").presentThisAsModal {
                it.width = 500.0
                it.height = 600.0
            }
            /*) {
                binding.showInputOutput.text = "= \"$input\"))"
            }*/
        }
        binding.showInput.setOnAction {
            InputWorkScreen("").presentThisAsModal(configureStage = { it.height = 500.0; it.width = 400.0 })
        }
        binding.showOutput.setOnAction {
            OutputWorkScreen().presentThisAsModal {
                //binding.showOutput.text = "output (got \"$input\" back))"
            }
//            presentView() {
//
//            }
        }
    }

    override fun CoroutineScope.createNewUi() = asyncMain {
        EmptyWorkScreenView()
    }
}


