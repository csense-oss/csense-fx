package csense.example.app

import csense.javafx.extensions.asyncDefault
import csense.javafx.views.BaseViewInput
import csense.javafx.views.OnViewSetup
import csense.javafx.views.data.InUiUpdateInput
import javafx.scene.control.Button
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import java.lang.RuntimeException


/*Input screen */
class InputWorkScreen(input: String) : BaseViewInput<Button, Button, String, Int>(input) {
    override suspend fun loadView(onViewSetup: OnViewSetup): Button {
        return Button()
    }


    //initial data concept (bad)
    val loadFile = inBackgroundAsync {
        delay(5000)
        "some content"
    }

    override suspend fun transformInput(input: String): Int {
        delay(2000)
        return input.toIntOrNull() ?: -1
    }


    override fun bindView(loaded: Button): Button {
        loaded.text = "is loading data"
        return loaded
    }


    override fun InUiUpdateInput<Button, Int>.onStartData() {
        binding.text = "value is = $input"
        binding.setOnAction {
            onButtonClicked()
        }
    }

    fun onButtonClicked() = backgroundToUi(
        computeAction = {
            loadFile.await()
        },
        uiAction = {
            binding.text = "file content = $input"
            binding.setOnAction {
                onButtonBadClicked()
            }
        })


    var shouldCrash = true

    fun onButtonBadClicked() = backgroundToUi({
        val a = scope.asyncDefault {
            println("a")
            delay(500)
            println("a2")
            22
        }
        val b = scope.asyncDefault {
            println("b")
            delay(500)
            println("b2")
            42
        }
        val c = scope.asyncDefault {
            if (shouldCrash) {
                shouldCrash = false
                throw RuntimeException("java error ! :/")
            }
            44
        }
        listOf(a, b, c).joinAll()
        "${a.await() + b.await() + c.await()}"
    }, {
        binding.text = "result = $input"
        throw Exception("should crash application")
    })
}
