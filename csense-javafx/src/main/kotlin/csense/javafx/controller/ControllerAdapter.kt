package csense.javafx.controller

import csense.javafx.extensions.parent.*
import csense.javafx.viewdsl.InViewDsl
import csense.javafx.viewdsl.text
import csense.javafx.views.base.BaseView
import javafx.scene.layout.Pane

class ControllerAdapter(
        controller: BaseView<*, *>,
        container: @InViewDsl Pane
) {
    init {
        val text = container.text { text = "loading" }
        controller.replaceToView(container, text)
    }
}