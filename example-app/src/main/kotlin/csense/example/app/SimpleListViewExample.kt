package csense.example.app

import csense.javafx.viewdsl.hBox
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.stackPane
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.widgets.list.SimpleListView
import csense.javafx.widgets.list.SimpleListViewAdapter
import csense.javafx.widgets.list.SimpleListViewRender
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.collections.list.repeatToSize
import csense.kotlin.extensions.coroutines.asyncMain
import csense.kotlin.extensions.type
import javafx.scene.Parent
import javafx.scene.control.Label
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred


class SimpleListExampleUi : BaseView<SimpleListView> {
    override val root: SimpleListView = SimpleListView()

}

class SimpleListExample : BaseEmptyViewController<SimpleListExampleUi>() {

    @InUi
    private val adapter = SimpleListViewAdapter()

    override fun InUiUpdateEmpty<SimpleListExampleUi>.onReady() {
        binding.root.adapter = adapter
//        val items = (0..50).map { SimpleListExampleItem("N:$it") } +
//                SimpleListExampleItem3("type 3")
        val items = listOf(SimpleListExampleItem3("first")) + listOf(
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"),
                SimpleListExampleItem("normal"),
                SimpleListExampleItem2("unormal"))
                .repeatToSize(100) + SimpleListExampleItem3("type 3")

        adapter.setSection(1, items)
//        currentStage?.scene?.logFps()
    }

    override fun CoroutineScope.createNewUi(): Deferred<SimpleListExampleUi> = asyncMain {
        SimpleListExampleUi()
    }
}

class SimpleListExampleItem(val text: String) : SimpleListViewRender<SimpleListExampleItemUi>(type()) {
    private val ourText = text + " - omg"
    override fun onRender(renderTo: SimpleListExampleItemUi) {
        renderTo.textLabel.text = ourText
    }

    override fun createUi(): SimpleListExampleItemUi = SimpleListExampleItemUi()
}

class SimpleListExampleItemUi : BaseView<Parent> {
    val textLabel: Label
    override val root: Parent = stackPane {
        textLabel = label { text = "not set so far" }
    }
}

class SimpleListExampleItem2(val text: String) : SimpleListViewRender<SimpleListExampleItem2Ui>(type()) {
    override fun onRender(renderTo: SimpleListExampleItem2Ui) {
        renderTo.myLabel.text = text + " - Nooo"
    }

    override fun createUi(): SimpleListExampleItem2Ui = SimpleListExampleItem2Ui()
}

class SimpleListExampleItem2Ui : BaseView<Parent> {
    val myLabel: Label
    override val root: Parent = hBox {
        myLabel = label { text = "not set so far22" }
    }
}

class SimpleListExampleItem3(text: String) : SimpleListViewRender<SimpleListExampleItemUi>(type()) {
    private val ourText = text + " - Moo"
    override fun onRender(renderTo: SimpleListExampleItemUi) {
        renderTo.textLabel.text = ourText
    }

    override fun createUi(): SimpleListExampleItemUi = SimpleListExampleItemUi()
}
