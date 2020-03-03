package csense.example.app.widgets

import csense.javafx.css.SingleBackgroundColor
import csense.javafx.extensions.scene.fillParent
import csense.javafx.extensions.scene.control.setOnClickAsync
import csense.javafx.viewdsl.*
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseViewParent
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.widgets.list.SimpleListViewRender
import csense.javafx.widgets.tabs.TabListPane
import csense.javafx.widgets.tabs.TabListPaneTab
import csense.javafx.widgets.tabs.tabListPane
import csense.kotlin.extensions.coroutines.asyncDefault
import csense.kotlin.extensions.type
import javafx.geometry.Orientation
import javafx.scene.Parent
import javafx.scene.control.Cell
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

class TabsExampleScreenUi : BaseViewParent {
    val tabPane: TabListPane
    override val root: Parent = vBox {
        tabPane = tabListPane(Orientation.VERTICAL) {
            setTabs(
                    TabListPaneTab(
                            TabsExampleListRender(0, ::selectTabAt),
                            csense.javafx.viewdsl.stackPane {
//                                text = "1"
                                background = SingleBackgroundColor(Color.BLUE)
                            }),
                    TabListPaneTab(
                            TabsExampleListRender(1, ::selectTabAt),
                            csense.javafx.viewdsl.stackPane {
//                                text = "2"
                                background = SingleBackgroundColor(Color.GREEN)
                            }),
                    TabListPaneTab(
                            TabsExampleListRender(2, ::selectTabAt),
                            csense.javafx.viewdsl.stackPane {
//                                text = "1"
                                background = SingleBackgroundColor(Color.RED)
                            }),
                    TabListPaneTab(
                            TabsExampleListRender(3, ::selectTabAt),
                            csense.javafx.viewdsl.stackPane {
//                                text = "2"
                                background = SingleBackgroundColor(Color.BLANCHEDALMOND)
                            })
            )
            fillParent()
        }
    }

}

class TabsExampleScreen : BaseEmptyViewController<TabsExampleScreenUi>() {
    override fun InUiUpdateEmpty<TabsExampleScreenUi>.onReady() {
        binding.tabPane.selectTabAt(0)
    }

    override fun CoroutineScope.createNewUi(): Deferred<TabsExampleScreenUi> = asyncDefault {
        TabsExampleScreenUi()
    }

}

class TabsExampleListRenderUi : BaseViewParent {
    override val root = stackPane {
        background = SingleBackgroundColor(Color.LIGHTBLUE)
        label {
            text = "omg22"
            fillParent()
            textAlignment = TextAlignment.CENTER
        }
    }
}

class TabsExampleListRender(val index: Int, val setIndex: (Int) -> Unit) : SimpleListViewRender<TabsExampleListRenderUi>(type()) {
    override fun createUi(): TabsExampleListRenderUi {
        return TabsExampleListRenderUi()
    }

    override fun onRender(renderTo: TabsExampleListRenderUi) {

    }

    override fun onRender(renderTo: TabsExampleListRenderUi, cell: Cell<*>) {
        super.onRender(renderTo, cell)
        renderTo.root.background = SingleBackgroundColor(Color.BEIGE)
        cell.setOnClickAsync {
            setIndex(index)
        }
//        cell.graphic?.fillParent()
//        renderTo.root.background = SingleBackgroundColor(Color.MAGENTA)

    }

}

