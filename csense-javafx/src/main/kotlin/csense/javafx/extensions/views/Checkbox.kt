package csense.javafx.extensions.views

import javafx.scene.control.CheckBox

val CheckBox.isNotSelected: Boolean get() = !isSelected
