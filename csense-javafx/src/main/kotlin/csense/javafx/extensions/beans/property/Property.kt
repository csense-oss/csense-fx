package csense.javafx.extensions.beans.property

import javafx.beans.property.Property
import javafx.beans.value.WritableValue
import kotlin.reflect.KProperty

/**
 * Inverse of "isBound"
 */
val Property<*>.isNotBound: Boolean
    get() = !isBound

//TODO is this precise enough ?
operator fun <T> WritableValue<T>.setValue(container: Any, property: KProperty<*>, value: T) = setValue(value)

