package csense.javafx.widgets.tabs

import csense.javafx.extensions.parent.addToFrontF
import csense.javafx.extensions.scene.fillParent
import csense.javafx.extensions.scene.fillStackPane
import csense.javafx.viewdsl.hBox
import csense.javafx.viewdsl.scrollPane
import csense.javafx.viewdsl.vBox
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.BaseViewParent
import csense.javafx.widgets.list.SimpleDelegatingListViewRender
import csense.javafx.widgets.list.SimpleListView
import csense.javafx.widgets.list.SimpleListViewAdapter
import csense.javafx.widgets.list.SimpleListViewRender
import csense.kotlin.Function0R
import csense.kotlin.ReceiverFunctionUnit
import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.collections.getSafe
import csense.kotlin.extensions.collections.setAll
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class TabListPane(orientation: Orientation) : StackPane() {


    private var selectedTab: TabListPaneTab<*>? = null

    private val tabs: MutableList<TabListPaneTab<*>> = mutableListOf()
    private val tabsAdapter: SimpleListViewAdapter = SimpleListViewAdapter()

    private val tabsList: SimpleListView = SimpleListView(tabsAdapter)

    private val rootPane: Pane = orientation.toBox()
    private val contentPane: Pane = StackPane()

    init {
        rootPane.children.setAll(tabsList, contentPane)
        contentPane.fillParent()
        tabsList.orientation = orientation
        children.setAll(rootPane)
    }

    fun addTab(tab: TabListPaneTab<*>) {
        tabs += tab
        tabsAdapter.setSection(0, listOf(tab.header))
    }

    fun addTabAt(tab: TabListPaneTab<*>, @IntLimit(from = 0) at: Int) {
        tabs.add(at, tab)
        tabsAdapter.setSection(0, listOf(tab.header))//TODO
    }

    fun removeTab(tab: TabListPaneTab<*>) {
        tabs -= tab
        tabsAdapter.setSection(0, listOf())
    }

    fun clearTabs() {
        tabs.clear()
//        tabsAdapter.clear()
    }

    fun setTabs(vararg newTabs: TabListPaneTab<*>) {
        tabs.setAll(*newTabs)
        tabsAdapter.setSection(0, newTabs.map { it.header })
    }

    fun setTabs(tabs: List<TabListPaneTab<*>>) {

    }

    fun selectTabAt(@IntLimit(from = 0) index: Int) {
        val tab = tabs.getSafe(index) ?: return
        selectTab(tab)
    }

    fun selectTab(tab: TabListPaneTab<*>) {
        if (tab == selectedTab) {
            return
        }
        val node = tab.content
        contentPane.children.setAll(node)
        node.fillStackPane()
        selectedTab = tab
    }
}

class TabListPaneTab<HeaderUi : BaseView<*>>(
        val header: SimpleListViewRender<HeaderUi>,
        val content: Node)

inline fun <HeaderUi : BaseView<*>>  TabListPaneTab(
        header: SimpleListViewRender<HeaderUi>,
        crossinline configureNode: ReceiverFunctionUnit<StackPane>): TabListPaneTab<HeaderUi> {
    contract {
        callsInPlace(configureNode, InvocationKind.EXACTLY_ONCE)
    }
    val stackPane = StackPane()
    configureNode(stackPane)
    return TabListPaneTab(header, stackPane)
}

inline fun <HeaderUi : BaseView<*>>  TabListPaneTabScrollableVbox(
        header: SimpleListViewRender<HeaderUi>,
        crossinline configureNode: ReceiverFunctionUnit<VBox>): TabListPaneTab<HeaderUi> {
    contract {
        callsInPlace(configureNode, InvocationKind.EXACTLY_ONCE)
    }
    val box = VBox()
    configureNode(box)
    return TabListPaneTab(header, ScrollPane(box))
}

inline fun <HeaderUi : BaseView<*>> TabListPaneTabNew(
        header: SimpleListViewRender<HeaderUi>,
        crossinline createNode: Function0R<Node>): TabListPaneTab<HeaderUi> {
    contract {
        callsInPlace(createNode, InvocationKind.EXACTLY_ONCE)
    }
    return TabListPaneTab(header, createNode())
}

fun Orientation.toBox(): Pane = when (this) {
    Orientation.HORIZONTAL -> vBox { }
    Orientation.VERTICAL -> hBox { }
}


inline fun Pane.tabListPane(
        orientation: Orientation,
        crossinline configureAction: ReceiverFunctionUnit<TabListPane>): TabListPane {
    contract {
        callsInPlace(configureAction, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(TabListPane(orientation)).apply(configureAction)
}

//fun <HeaderUi : BaseView<*>> TabListPaneTab<HeaderUi>.toInnerTabListRender(
//): SimpleListViewRender<*>{
////    return object : SimpleDelegatingListViewRender(this){
////
////    }
//}

//private class InnerTabListRender(val header: TabListPaneTab<*>, selectTabFunction: Function0R<TabListPaneTab>) : SimpleDelegatingListViewRender<*>(header.header) {
//    override fun createUi(): BaseView<*> {
//        return header.header.createUi()
//    }
//
//    override fun onRender(renderTo: BaseViewParent) {
//        TODO("Not yet implemented")
//    }
//}