package csense.javafx.widgets.input

import csense.javafx.css.*
import javafx.beans.property.*
import javafx.scene.control.*
import javafx.scene.control.skin.*
import javafx.scene.paint.*
import csense.javafx.extensions.beans.property.*
import csense.javafx.extensions.parent.*
import csense.javafx.extensions.scene.paint.*
import csense.javafx.viewdsl.*
import csense.kotlin.annotations.threading.*
import javafx.scene.layout.*
import kotlin.contracts.*


class TextAreaStyleable : TextArea() {
    
    init {
        styleClass.clear()
    }
    
    override fun createDefaultSkin(): Skin<*> {
        return TextAreaStyleableSkin(this)
    }
    
    val textFillProperty: ObjectProperty<Paint> = SimpleObjectProperty<Paint>(
            Color.BLACK
    )
    var textFill by textFillProperty
    
    
    val highlightFillProperty: ObjectProperty<Paint> = SimpleObjectProperty<Paint>(
            Color.GREY
    )
    var highlightFill by highlightFillProperty
    
    val highlightTextFillProperty: ObjectProperty<Paint> = SimpleObjectProperty<Paint>(
            Color.BLACK
    )
    var highlightTextFill by highlightTextFillProperty
    
    val promptTextFillProperty: ObjectProperty<Paint> = SimpleObjectProperty<Paint>(
            Color.GREY
    )
    var promptTextFill by highlightTextFillProperty
}

class TextAreaStyleableSkin(control: TextAreaStyleable) : TextAreaSkin(control) {
    init {
        textFillProperty().bind(control.textFillProperty)
        highlightFillProperty().bind(control.highlightFillProperty)
        highlightTextFillProperty().bind(control.highlightTextFillProperty)
        promptTextFillProperty().bind(control.promptTextFillProperty)
        //clear scrollview
        val innerScrollView = (children.firstOrNull() as? ScrollPane)
        innerScrollView?.styleClass?.clear()
    }
}

@InUi
inline fun Pane.textareaStyleable(
        crossinline action: ScopedViewDsl<TextAreaStyleable>
): TextAreaStyleable {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TextAreaStyleable()).apply(action)
}
