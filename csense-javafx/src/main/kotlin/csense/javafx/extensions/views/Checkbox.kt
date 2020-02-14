package csense.javafx.extensions.views

import javafx.scene.control.CheckBox

val CheckBox.isNotSelected: Boolean get() = !isSelected

var CheckBox.isChecked: Boolean
    get() = isSelected
    set(newValue) {
        isSelected = newValue
    }
