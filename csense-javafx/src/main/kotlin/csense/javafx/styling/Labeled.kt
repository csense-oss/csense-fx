package csense.javafx.styling

import csense.javafx.annotations.*
import javafx.scene.control.*
import javafx.scene.paint.*

/**
 * Exposes the textFill as a color (if it is)
 */

var Labeled.textColor: Color?
    @InUI
    get() = textFill as? Color
    @InUI
    set(value) {
        textFill = value
    }


