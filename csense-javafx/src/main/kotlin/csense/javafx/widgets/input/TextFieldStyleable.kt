package csense.javafx.widgets.input

import javafx.beans.property.*
import javafx.scene.control.*
import javafx.scene.control.skin.*
import javafx.scene.paint.*
import csense.javafx.extensions.beans.property.*


open class TextFieldStyleable : TextField() {
    
    override fun createDefaultSkin(): Skin<*> {
        return TextFieldStyleableSkin(this)
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

class TextFieldStyleableSkin(control: TextFieldStyleable) : TextFieldSkin(control) {
    init {
        textFillProperty().bind(control.textFillProperty)
        highlightFillProperty().bind(control.highlightFillProperty)
        highlightTextFillProperty().bind(control.highlightTextFillProperty)
        promptTextFillProperty().bind(control.promptTextFillProperty)
    }
}