package csense.javafx.widgets

import csense.javafx.extensions.parent.*
import csense.javafx.viewdsl.*
import csense.kotlin.annotations.threading.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.control.skin.*
import javafx.scene.layout.*
import kotlin.contracts.*

/**
 * an unstyled scrollpane, so no background or alike "hidden" designs
 */

open class ScrollPaneEmpty(content: Node? = null) : ScrollPane() {
    init {
        if (content != null) {
            this.content = content
        }
    }
    
    override fun createDefaultSkin(): Skin<*> {
        val skin = ScrollPaneSkin(this)
        skin.node.styleClass.clear()
        return skin
    }
}


@InUi
inline fun scrollPaneEmpty(
        crossinline action: @InViewDsl ScopedViewDsl<ScrollPaneEmpty>
): ScrollPaneEmpty {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ScrollPaneEmpty().apply(action)
}


@InUi
inline fun Pane.scrollPaneEmpty(
        @InUi crossinline action: ScopedViewDsl<ScrollPaneEmpty>
): ScrollPaneEmpty {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ScrollPaneEmpty()).apply(action)
}
