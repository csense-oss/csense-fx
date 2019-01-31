package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseViewInOutput
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent
import javafx.scene.control.Button
import kotlinx.coroutines.Deferred


interface InOutWorkScreenCallbacks {
    fun onEnd()
    fun onMagic()
}


class InOutWorkScreenBinding(onViewSetup: OnViewSetup) : LoadViewAble<Parent>(onViewSetup) {
    val mainButton: Button
    val otherButton: Button
    override val root: Parent = vBox {
        mainButton = button("42")
        otherButton = button("otherFunction")
    }
}


/*
* IO screen
*/
class InOutWorkScreen(input: String) : BaseViewInOutput<Unit, InOutWorkScreenBinding, String, String, Int?>(
    input,
    { _: Unit, onViewSetup: OnViewSetup -> InOutWorkScreenBinding(onViewSetup) }
), InOutWorkScreenCallbacks {


//    private val view: Button = Button()
//    override suspend fun loadView(onViewSetup: OnViewSetup): Button = view

    override suspend fun loadView() {}

    override fun InUiUpdateInput<InOutWorkScreenBinding, String>.onStartData() {
        println("---on start data--")
//        binding.text = input
//        binding.setOnAction {
//            currentWindow?.hide()
//        }
        binding.mainButton.setOnAction { onEnd() }
        binding.otherButton.setOnAction { onMagic() }
    }

    override suspend fun transformInput(input: String): String = "$input + 88"


    override fun createResultAsync(): Deferred<Int?> = inUiAsync {
        binding.mainButton.text.toIntOrNull()
    }

    override fun onEnd() {

    }

    override fun onMagic() {

    }
}