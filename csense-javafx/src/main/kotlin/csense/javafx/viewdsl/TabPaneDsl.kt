@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@InUi
inline fun TabPane.tab(
        title: String,
        crossinline action: ScopedViewDsl<Tab>
): Tab {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val tab = Tab(title)
    tabs += tab
    return tab.apply(action)
}

@InUi
inline fun <T : Node> TabPane.tabWith(
        title: String,
        content: T
): Tab {
    val tab = Tab(title, content)
    tabs.add(tab)
    return tab
}

@InUi
inline fun TabPane.tabWithVBox(
        title: String,
        crossinline action: ScopedViewDsl<VBox>
): Tab {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val vBox = vBox {}
    return tabWith(title, vBox).also {
        action(vBox)
    }
}

@InUi
inline fun TabPane.tabWithScrollableVBox(
        title: String,
        crossinline action: ScopedViewDsl<VBox>
): Tab {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val vBox = vBox {}
    return tabWith(title, scrollPane { content = vBox }).also {
        action(vBox)
    }
}

