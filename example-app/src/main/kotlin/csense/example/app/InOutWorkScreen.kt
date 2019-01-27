package csense.example.app

import csense.javafx.views.BaseViewInOutput
import csense.javafx.views.OnViewSetup
import csense.javafx.views.data.InUiUpdateInput
import javafx.scene.control.Button
import kotlinx.coroutines.Deferred


/*
* IO screen
*/
class InOutWorkScreen(input: String) : BaseViewInOutput<Button, Button, String, String, Int?>(input) {
    private val view : Button = Button()
    override suspend fun loadView(onViewSetup: OnViewSetup): Button = view

    override fun bindView(loaded: Button): Button {
        println("binding")
        return loaded
    }

    override fun InUiUpdateInput<Button, String>.onStartData() {
        println("---on start data--")
        binding.text = input
        binding.setOnAction {
            currentWindow?.hide()
        }
    }

    override suspend fun transformInput(input: String) = "$input + 88"


    override fun createResultAsync(): Deferred<Int?> = inUiAsync {
        binding.text.toIntOrNull()
    }

}