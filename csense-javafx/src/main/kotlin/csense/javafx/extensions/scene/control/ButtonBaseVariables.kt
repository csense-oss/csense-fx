package csense.javafx.extensions.scene.control

import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ButtonBase

inline val ButtonBase.armedProperty: ReadOnlyBooleanProperty
    inline get() = armedProperty()

inline val ButtonBase.onActionProperty: ObjectProperty<EventHandler<ActionEvent>>
    inline get() = onActionProperty()