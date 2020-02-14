package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseViewControllerOutput
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.BaseView
import csense.kotlin.EmptyFunction
import csense.kotlin.extensions.coroutines.asyncMain
import javafx.scene.Parent
import javafx.scene.control.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred


class OutputWorkScreenView(
        callbacks: EmptyFunction
) : BaseView<Parent> {

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
class OutputWorkScreen : BaseViewControllerOutput<OutputWorkScreenView, String>() {


    override fun InUiUpdateEmpty<OutputWorkScreenView>.onReady() {
        binding.a.text = "swag"
    }

//    override suspend fun loadView() = this

    override fun createResultAsync(): Deferred<String> = inUiAsync {
        binding.a.text
    }

    fun ontest() {
        println("tests")
    }

    override fun CoroutineScope.createNewUi(): Deferred<OutputWorkScreenView> = asyncMain {
        OutputWorkScreenView(this@OutputWorkScreen::ontest)
    }
}
