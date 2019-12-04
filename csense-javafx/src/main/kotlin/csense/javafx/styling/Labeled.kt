package csense.javafx.styling

import csense.kotlin.annotations.threading.*
import javafx.scene.control.*
import javafx.scene.paint.*

/**
 * Exposes the textFill as a color (if it is)
 */

var Labeled.textColor: Color?
    @InUi
    get() = textFill as? Color
    @InUi
    set(value) {
        textFill = value
    }


