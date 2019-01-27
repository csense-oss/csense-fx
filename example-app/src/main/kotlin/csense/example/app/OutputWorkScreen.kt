package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.gridPane
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseView
import csense.javafx.views.BaseViewOutput
import csense.javafx.views.OnViewSetup
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import kotlinx.coroutines.Deferred
import kotlin.contracts.contract


abstract class LoadViewAble<RootType : Parent>(onViewSetup: OnViewSetup) {
    abstract val root: RootType
}

interface SomeOutputScreenCallbacks {
    fun ontest()
}

class OutputWorkScreenView(
    callbacks: SomeOutputScreenCallbacks,
    onViewSetup: OnViewSetup
) : LoadViewAble<Parent>(onViewSetup) {

    val a: Button
    override val root =
        vBox {
            vBox {
                spacing = 2.0
                a = button("test", callbacks::ontest) {
                }
            }
        }

}

/*
 O screen
  */
class OutputWorkScreen : BaseViewOutput<Button, Button, String>(), SomeOutputScreenCallbacks {


    override suspend fun loadView(onViewSetup: OnViewSetup): Button {
        val view = OutputWorkScreenView(this, onViewSetup)
        view.a.text = "test 2 "
        return Button()
    }

    override fun createResultAsync(): Deferred<String> = inUiAsync {
        binding.text
    }

    override fun bindView(loaded: Button): Button {
//        loaded.text = "compute result"
        loaded.setOnAction {
            //hmm how to interact with javafx here ?
            inUi {
                currentWindow?.hide()
            }

        }
        return loaded
    }

    override fun ontest() {

    }
}
