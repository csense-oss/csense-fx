@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.InUi
import javafx.scene.control.ContextMenu
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.SeparatorMenuItem
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@InUi
inline fun contextMenu(
        crossinline action: @InViewDsl ScopedViewDsl<ContextMenu>
): ContextMenu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ContextMenu().apply(action)
}


@InUi
inline fun menu(
        crossinline action: @InViewDsl ScopedViewDsl<Menu>
): Menu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Menu().apply(action)
}

inline fun Menu.menu(
        crossinline action: @InViewDsl ScopedViewDsl<Menu>
): Menu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return csense.javafx.viewdsl.menu(action).also {
        items.addAll(it)
    }
}


inline fun ContextMenu.menu(
        crossinline action: @InViewDsl ScopedViewDsl<Menu>
): Menu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return csense.javafx.viewdsl.menu(action).also {
        items.addAll(it)
    }
}

@InUi
inline fun menuItem(
        crossinline action: @InViewDsl ScopedViewDsl<MenuItem>
): MenuItem {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return MenuItem().apply(action)
}


@InUi
inline fun ContextMenu.menuItem(
        crossinline action: @InViewDsl ScopedViewDsl<MenuItem>
) =
        menuItem("", action)

@InUi
inline fun ContextMenu.menuItem(
        text: String,
        crossinline action: @InViewDsl ScopedViewDsl<MenuItem>
) {
    val item = MenuItem(text)
    items.add(item)
    action(item)
}

@InUi
inline fun separatorMenuItem(): SeparatorMenuItem = SeparatorMenuItem()

@InUi
inline fun Menu.separatorMenuItem(): SeparatorMenuItem = SeparatorMenuItem().also {
    items.add(it)
}

@InUi
inline fun ContextMenu.separatorMenuItem() {
    items.add(csense.javafx.viewdsl.separatorMenuItem())
}
