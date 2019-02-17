package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseViewOutput
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.kotlin.EmptyFunction
import javafx.scene.Parent
import javafx.scene.control.Button
import kotlinx.coroutines.Deferred


class OutputWorkScreenView(
    callbacks: EmptyFunction,
    onViewSetup: OnViewSetup
) : LoadViewAble<Parent>(onViewSetup) {

    val a: Button
    override val root =
        vBox {
            vBox {
                spacing = 2.0
                a = button("test", { callbacks() }) {
                }
            }
        }
}

/*
 O screen
  */
class OutputWorkScreen : BaseViewOutput<OutputWorkScreen, OutputWorkScreenView, String>(
    { item: OutputWorkScreen, onViewSetup: OnViewSetup -> OutputWorkScreenView(item::ontest, onViewSetup) }
) {


    override fun InUiUpdateEmpty<OutputWorkScreenView>.onReady() {
        binding.a.text = "swag"
    }

    override suspend fun loadView() = this

    override fun createResultAsync(): Deferred<String> = inUiAsync {
        binding.a.text
    }

    fun ontest() {
        println("tests")
    }
}
