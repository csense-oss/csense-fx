@file:Suppress("unused")

package csense.javafx.tools.hookpoints

import csense.javafx.viewdsl.menu
import csense.javafx.viewdsl.setAction
import csense.javafx.views.base.BaseViewController
import javafx.scene.control.MenuBar

/**
 * Creates a reload button for the given menubar
 */
fun MenuBar.reloadButton(view: BaseViewController<*>, creator: () -> BaseViewController<*>) {
    menu("Reload view") {
        setAction {
            println("reloading view")
            view.inUi {
                val orgHeight: Double = currentWindow?.height ?: 800.0
                val orgWidth: Double = currentWindow?.width ?: 800.0
                view.transitionTo(creator()) {
                    it.height = orgHeight
                    it.width = orgWidth
                }
            }
        }
    }
}
