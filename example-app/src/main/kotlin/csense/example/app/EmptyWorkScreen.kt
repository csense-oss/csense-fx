package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyView
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.OnViewSetup
import javafx.scene.control.Button
import javafx.scene.layout.VBox


class EmptyWorkScreenView(onViewSetup: OnViewSetup) : LoadViewAble<VBox>(onViewSetup) {

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
class EmptyWorkScreen : BaseEmptyView<Unit, EmptyWorkScreenView>(
    { _: Unit, onViewSetup: OnViewSetup -> EmptyWorkScreenView(onViewSetup) }
) {


    override suspend fun loadView() = Unit

    override fun InUiUpdateEmpty<EmptyWorkScreenView>.onReady() {
        binding.showInputOutput.setOnAction {

            //            presentView(InOutWorkScreen("123")) {
//                binding.showInputOutput.text = "= \"$input\"))"
//            }
        }
        binding.showInput.setOnAction {
            InputWorkScreen("").presentModal()
        }
        binding.showOutput.setOnAction {
            //            presentView(OutputWorkScreen()) {
//                binding.showOutput.text = "output (got \"$input\" back))"
//            }
        }
    }


//    override fun bindView(loaded: EmptyWorkScreenView): EmptyWorkScreenView {
//        loaded.showInputOutput.setOnAction {
//            presentView(InOutWorkScreen("123")) {
//                binding.showInputOutput.text = "= \"$input\"))"
//            }
//        }
//        loaded.showInput.setOnAction {
//
//            InputWorkScreen("").presentModal()
//        }
//        loaded.showOutput.setOnAction {
//            presentView(OutputWorkScreen()) {
//                binding.showOutput.text = "output (got \"$input\" back))"
//            }
//        }
//        return loaded
//    }
}


