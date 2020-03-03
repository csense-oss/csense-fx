package csense.javafx.extensions.scene.control

import javafx.beans.property.StringProperty
import javafx.scene.control.TextInputControl


inline val TextInputControl.textProperty: StringProperty
    inline get() = textProperty()

//inline val TextInputControl.textProperty
//    inline get() = textProperty()