@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.javafx.viewdsl

import csense.javafx.annotations.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*

@InUI
inline fun TabPane.tab(
        title: String,
        crossinline action: ScopedViewDsl<Tab>
): Tab {
    val tab = Tab(title)
    tabs += tab
    return tab.apply(action)
}

@InUI
inline fun <T : Node> TabPane.tabWith(
        title: String,
        content: T
): Tab {
    val tab = Tab(title, content)
    tabs.add(tab)
    return tab
}

@InUI
inline fun TabPane.tabWithVBox(
        title: String,
        crossinline action: ScopedViewDsl<VBox>
): Tab {
    val vBox = vBox {}
    return tabWith(title, vBox).also {
        action(vBox)
    }
}