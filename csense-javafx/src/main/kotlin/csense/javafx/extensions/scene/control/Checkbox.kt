package csense.javafx.extensions.scene.control

import javafx.scene.control.CheckBox

val CheckBox.isNotSelected: Boolean get() = !isSelected

var CheckBox.isChecked: Boolean
    get() = isSelected
    set(newValue) {
        isSelected = newValue
    }
