@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.viewdsl

import csense.javafx.controller.*
import csense.javafx.views.base.*
import javafx.scene.control.*
import javafx.scene.layout.*

//regarding using / inline ect controllers.


inline fun TabPane.tabWithController(
        title: String,
        baseView: BaseView<*, *>): Tab {
    val containerPane = Pane().apply {
        useController(baseView)
    }
    return tabWith(title, containerPane)
}