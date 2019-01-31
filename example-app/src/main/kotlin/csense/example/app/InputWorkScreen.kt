package csense.example.app

import csense.javafx.viewdsl.button
import csense.javafx.views.BaseViewInput
import csense.javafx.views.base.InUiUpdateInput
import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import csense.kotlin.extensions.coroutines.asyncDefault
import javafx.scene.control.Button
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll


class InputWorkScreenView(onViewSetup: OnViewSetup) : LoadViewAble<Button>(onViewSetup) {
    override val root: Button = button("loading...")
}

class InputWorkScreen(input: String) : BaseViewInput<Unit, InputWorkScreenView, String, Int>(
    input,
    { _: Unit, onViewSetup: OnViewSetup -> InputWorkScreenView(onViewSetup) }
) {


    override suspend fun loadView() {}


    //initial data concept (bad)
    val loadFile = inBackgroundAsync {
        delay(500)
        "some content"
    }

    override suspend fun transformInput(input: String): Int {
        delay(200)
        return input.toIntOrNull() ?: -1
    }


    override fun InUiUpdateInput<InputWorkScreenView, Int>.onStartData() {
        binding.root.text = "value is = $input"
        binding.root.setOnAction {
            onButtonClicked()
        }
    }

    fun onButtonClicked() = backgroundToUi(
        computeAction = {
            loadFile.await()
        },
        uiAction = {
            binding.root.text = "file content = $input"
            binding.root.setOnAction {
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
        binding.root.text = "result = $input"
        throw Exception("should crash application")
    })
}
