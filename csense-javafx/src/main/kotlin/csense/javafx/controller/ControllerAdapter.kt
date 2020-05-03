@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.controller

import csense.javafx.viewdsl.*
import csense.javafx.views.base.*
import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import javafx.scene.*
import javafx.scene.layout.*

/**
 *
 * @receiver Pane
 * @param controller BaseView<*, *>
 */
//@InUi
//inline fun <T : BaseViewController<*>> Pane.useController(
//        controller: T,
//        crossinline action: ScopedViewDsl<T>
//): T {
//    contract {
//        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
//    }
//    return this.useController(controller).apply(action)
//}

@InUi
inline fun <T : BaseViewController<*>> Pane.useController(controller: T): T {
    val text = this.text { text = "loading" } //TODO change this..
    controller.replaceToView(this, text)
    return controller
}

@InUi
inline fun <T : BaseViewController<VM>, VM : BaseView<Parent>> Pane.useController(
        controller: T,
        noinline onView: ReceiverFunctionUnit<VM>
): T {
    val text = this.text { text = "loading" } //TODO change this..
    controller.replaceToView(this, text)
    controller.inUi{
        onView(binding)
    }
    return controller
}
