package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseViewControllerInOutput
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.BaseView
import csense.kotlin.extensions.coroutines.asyncDefault
//import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent
import javafx.scene.control.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred


interface InOutWorkScreenCallbacks {
    fun onEnd()
    fun onMagic()
}


class InOutWorkScreenBinding() : BaseView<Parent> {
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
class InOutWorkScreen(input: String) : BaseViewControllerInOutput<InOutWorkScreenBinding, String, String, Int?>(
        input
), InOutWorkScreenCallbacks {


//    private val view: Button = Button()
//    override suspend fun loadView(onViewSetup: OnViewSetup): Button = view

//    override suspend fun loadView() {}

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

    override fun CoroutineScope.createNewUi(): Deferred<InOutWorkScreenBinding> = asyncDefault {
        InOutWorkScreenBinding()
    }
}