package csense.example.app

import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseViewParent
import csense.javafx.views.base.InUiUpdateEmpty
import csense.kotlin.datastructures.values.*
import csense.kotlin.extensions.coroutines.asyncDefault
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

import java.io.InputStream

abstract class BaseEmptyFxmlViewController<T : BaseFXMLViewParent> : BaseEmptyViewController<T>() {
    override fun CoroutineScope.createNewUi(): Deferred<T> = asyncDefault {
        val controller = createController()
        val loader = FXMLLoader()
        loader.setController(controller)
        val resource = getFxmlInputStream()
        controller.fxmlRoot.value = loader.load(resource)
        controller
    }

    abstract fun createController(): T

    abstract fun getFxmlInputStream(): InputStream

}

abstract class BaseFXMLViewParent : BaseViewParent {

    internal val fxmlRoot by lazy {
        LockableValue<Parent>(1, FailedPane)
    }
    override val root: Parent
        get() = fxmlRoot.value
}

object FailedPane : Pane()

//Below is "user code", above is library code

class FXMLWorkScreen : BaseEmptyFxmlViewController<MyFxmlController>() {

    override fun InUiUpdateEmpty<MyFxmlController>.onReady() {
        binding.label2.text = "OMG"
    }

    override fun createController(): MyFxmlController = MyFxmlController()

    override fun getFxmlInputStream(): InputStream = javaClass.getResourceAsStream("/fxml/fxml_example.fxml")
}

class MyFxmlController : BaseFXMLViewParent() {
    @FXML
    lateinit var label2: Label

    @FXML
    fun buttonClicked(event: ActionEvent?) {
        println("im fxml'ing")
    }
}