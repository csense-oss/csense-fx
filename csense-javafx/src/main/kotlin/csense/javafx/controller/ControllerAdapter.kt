package csense.javafx.controller

import csense.javafx.viewdsl.*
import csense.javafx.views.base.*
import javafx.scene.layout.*

class ControllerAdapter(
        controller: BaseView<*, *>,
        container: @InViewDsl Pane
) {
    init {
        val text = container.text { text = "loading" }
        controller.replaceToView(container, text)
    }
}