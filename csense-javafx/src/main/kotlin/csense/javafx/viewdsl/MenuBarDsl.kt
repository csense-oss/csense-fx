@file:Suppress("NOTHING_TO_INLINE", "UNUSED")

package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.*
import csense.kotlin.*
import javafx.event.*
import javafx.scene.control.*
import kotlin.contracts.*

@InUi
inline fun MenuBar.menu(
    title: String,
    crossinline action: ScopedViewDsl<Menu> = {}
): Menu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Menu(title).apply(action).also {
        menus += it
    }
}

@InUi
inline fun Menu.menu(
    title: String,
    crossinline action: ScopedViewDsl<Menu> = {}
): Menu {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Menu(title).apply(action).also {
        items.add(it)
    }
}

@InUi
inline fun Menu.menuItem(
    title: String,
    crossinline action: ScopedViewDsl<MenuItem> = {}
): MenuItem {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return MenuItem(title).apply(action).also {
        items.add(it)
    }
}

/**
 *
 * Please only call this after the "children" have been set. otherwise it will not function properly.
 * @receiver Menu
 * @param onClicked Function1<Unit, Unit>
 */
@InUi
inline fun Menu.setAction(noinline onClicked: FunctionUnit<ActionEvent>): Menu {
    if (items.isEmpty()) {
        //apply fix for "semi" broken menu action handling
        //see https://stackoverflow.com/questions/10315774/javafx-2-0-activating-a-menu-like-a-menuitem/19006643#19006643
        this.menuItem("")
        this.addEventHandler(Menu.ON_SHOWN) { hide() }
        this.addEventHandler(Menu.ON_SHOWING) { fire() }
    }
    setOnAction { onClicked(it) }
    return this
}