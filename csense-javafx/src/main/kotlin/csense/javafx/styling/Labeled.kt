package csense.javafx.styling

import javafx.scene.control.*
import javafx.scene.paint.*

/**
 * Exposes the textFill as a color (if it is)
 */
var Labeled.textColor: Color?
    get() = textFill as? Color
    set(value) {
        textFill = value
    }


