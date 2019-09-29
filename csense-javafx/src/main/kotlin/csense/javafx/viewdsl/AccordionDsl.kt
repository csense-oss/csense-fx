package csense.javafx.viewdsl

import csense.javafx.annotations.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*

@InUI
inline fun Accordion.titledPane(
        crossinline action: ScopedViewDsl<TitledPane>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TitledPane().apply(action).also {
        panes.add(it)
    }
}

@InUI
inline fun Accordion.titledPane(
        title: String,
        crossinline action: ScopedViewDsl<TitledPane>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TitledPane().apply(action).also {
        it.text = title
        panes.add(it)
    }
}


@InUI
inline fun <T> Accordion.titledPaneWith(
        title: String,
        containerControl: T
): TitledPane where T : Node {
    return TitledPane().also {
        it.text = title
        panes.add(it)
        it.content = containerControl
    }
}


@InUI
inline fun Accordion.titledPaneWithVBox(
        title: String,
        crossinline action: ScopedViewDsl<VBox>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TitledPane().also {
        it.text = title
        panes.add(it)
        it.content = VBox().apply(action)
    }
}

