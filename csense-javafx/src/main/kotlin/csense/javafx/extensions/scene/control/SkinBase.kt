package csense.javafx.extensions.scene.control

import javafx.scene.control.Control
import javafx.scene.control.SkinBase

/**
 * Alias for the skinnable
 */
inline val <T : Control> SkinBase<T>.control: T
    inline get() = skinnable