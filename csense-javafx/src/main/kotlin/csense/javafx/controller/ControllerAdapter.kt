package csense.javafx.controller

import csense.javafx.viewdsl.InViewDsl
import csense.javafx.viewdsl.text
import csense.javafx.views.base.BaseView
import javafx.scene.layout.Pane

class ControllerAdapter(
    controller: BaseView<*, *>,
    container: @InViewDsl Pane
) {
    init {
        val index = container.children.size //where we place the loading placeholder.
        container.text { text = "loading" }
        controller.addToView(container).invokeOnCompletion {
            container.children.removeAt(index)
        }
    }
}