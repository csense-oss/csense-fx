package csense.javafx.widgets.tabs

import csense.javafx.extensions.listener.*
import csense.javafx.extensions.parent.*
import csense.javafx.extensions.scene.*
import csense.javafx.viewdsl.*
import csense.javafx.views.base.*
import csense.javafx.widgets.list.*
import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.*
import javafx.geometry.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*

class TabListPane(orientation: Orientation) : StackPane() {
    
    
    var selectedTab: TabListPaneTab<*>? = null
        private set
    
    private val tabs: MutableList<TabListPaneTab<*>> = mutableListOf()
    private val tabsAdapter: SimpleListViewAdapter = SimpleListViewAdapter()
    
    private val tabsList: SimpleListView = SimpleListView(tabsAdapter)
    
    private val rootPane: Pane = orientation.toBox()
    private val contentPane: Pane = StackPane()
    
    var onSetSelection: ((currentCell: Cell<*>) -> Unit)? = null
    var onRemoveSelection: ((currentCell: Cell<*>) -> Unit)? = null
    
    init {
        border = null
        background = null
        tabsList.border = null
        tabsList.style = ".list-view:focused {\n" +
                "-fx-faint-focus-color: transparent; \n" +
                "-fx-focus-color: transparent; \n" +
                "-fx-background-insets: 0, 0, 0, 0;" +
                "}"
        
        rootPane.children.setAll(tabsList, contentPane)
        contentPane.fillParent()
        tabsList.orientation = orientation
//        tabsList.selectionModel.selectedItemProperty().addListener(ChangeListenerNewValue {
//            (it?.render as? InnerTabListRender)?.toDelegateTo?.select()
//        })
        tabsList.focusModel.focusedItemProperty().addListener(ChangeListenerNewValue {
            (it?.render as? InnerTabListRender)?.toDelegateTo?.select()
        })
        children.setAll(rootPane)
    }
    
    fun addTab(tab: TabListPaneTab<*>) {
        tabs += tab
        tabsAdapter.addItem(0, tab.toInnerTabListRender(this))
    }
    
    fun addTabAt(tab: TabListPaneTab<*>, @IntLimit(from = 0) at: Int) {
        tabs.add(at, tab)
        tabsAdapter.setSection(0, listOf(tab.toInnerTabListRender(this)))//TODO
    }
    
    fun removeTab(tab: TabListPaneTab<*>) {
        tabs -= tab
        tabsAdapter.setSection(0, listOf())
    }
    
    fun clearTabs() {
        tabs.clear()
        tabsAdapter.clearAll()
    }
    
    fun setTabs(vararg newTabs: TabListPaneTab<*>) {
        setTabs(newTabs.toList())
    }
    
    fun setTabs(newTabs: List<TabListPaneTab<*>>) {
        if (newTabs.isEmpty()) {
            clearTabs()
            return
        }
        tabs.setAll(newTabs)
        tabsAdapter.setSection(0, newTabs.map { it.toInnerTabListRender(this) })
        selectTabAt(0)
    }
    
    
    fun selectTabAt(@IntLimit(from = 0) index: Int) =
            tabs.getOrNull(index)?.select().toUnit()
    
    fun selectTab(tab: TabListPaneTab<*>) = tab.select()
    
    private fun TabListPaneTab<*>.select() {
        if (this == selectedTab) {
            return
        }
        val selectedIndex = tabs.indexOfOrNull(this) ?: return
        val cell = tabsList.getCell(selectedIndex)
        lastSelectedCell?.background = null
        lastSelectedCell = cell
        
        selectedTab = this
        val node = this.content
        contentPane.children.setAll(node)
        node.fillStackPane()
        tabsList.focusModel.focus(selectedIndex)
//        cell.updateSelected(true)
//        tabs.indexOfOrNull(this)?.let {
//            tabsList.requestFocus()
//            tabsList.selectionModel.select(it)
//            tabsList.focusModel.focus(it)

//        }
    }
    
    private var lastSelectedCell: Cell<*>? = null
    
    fun selectTab(tab: TabListPaneTab<*>, renderTo: BaseView<*>) {
//        lastSelectedCell?.background = null
//        findCellFromRenderRoot(renderTo.root) {
//            lastSelectedCell = it
//        }
        tab.select()
    }
    
    private inline fun findCellFromRenderRoot(root: Node?, crossinline changeAction: FunctionUnit<Cell<*>>) {
        (root?.parent?.parent as? Cell<*>)?.let(changeAction)
    }
}

class TabListPaneTab<HeaderUi : BaseView<*>>(
        val header: SimpleListViewRender<HeaderUi>,
        val content: Node)

inline fun <HeaderUi : BaseView<*>> TabListPaneTab(
        header: SimpleListViewRender<HeaderUi>,
        crossinline configureNode: ReceiverFunctionUnit<StackPane>): TabListPaneTab<HeaderUi> {
    contract {
        callsInPlace(configureNode, InvocationKind.EXACTLY_ONCE)
    }
    val stackPane = StackPane()
    configureNode(stackPane)
    return TabListPaneTab(header, stackPane)
}

inline fun <HeaderUi : BaseView<*>> TabListPaneTabScrollableVbox(
        header: SimpleListViewRender<HeaderUi>,
        crossinline configureNode: ReceiverFunctionUnit<VBox>,
): TabListPaneTab<HeaderUi> {
    contract {
        callsInPlace(configureNode, InvocationKind.EXACTLY_ONCE)
    }
    val box = VBox()
    val scrollContent = ScrollPane(box).apply {
        isFitToHeight = true
        isFitToWidth = true
    }
    configureNode(box)
    return TabListPaneTab(header, scrollContent)
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

class InnerTabListRender<HeaderUi : BaseView<Parent>>(
        val tabListPane: TabListPane,
        val toDelegateTo: TabListPaneTab<HeaderUi>
) : SimpleListViewRender<HeaderUi>(toDelegateTo.header.uiClass) {
    
    override fun createUi(): HeaderUi = toDelegateTo.header.createUi()
    
    override fun onRender(renderTo: HeaderUi) = toDelegateTo.header.onRender(renderTo)
    
    override fun onRender(renderTo: HeaderUi, cell: Cell<*>) {
        super.onRender(renderTo, cell)
        cell.setOnMouseClicked {
            tabListPane.selectTab(toDelegateTo, renderTo)
        }
        toDelegateTo.header.onRender(renderTo, cell)
        if (tabListPane.selectedTab?.header == toDelegateTo.header) {
            tabListPane.onSetSelection?.invoke(cell)
//            cell.background = SingleBackgroundColor(Color.RED)
        } else {
            tabListPane.onRemoveSelection?.invoke(cell)
//            cell.background = null
        }
    }
    
}

fun <HeaderUi : BaseView<Parent>> TabListPaneTab<HeaderUi>.toInnerTabListRender(
        tabListPane: TabListPane
): SimpleListViewRender<HeaderUi> {
    return InnerTabListRender(tabListPane, this)
}