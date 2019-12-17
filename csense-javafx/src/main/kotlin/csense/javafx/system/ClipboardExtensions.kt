package csense.javafx.system

import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent

fun Clipboard.setString(value: String) {
    setContent(ClipboardContent().apply {
        putString(value)
    })
}
