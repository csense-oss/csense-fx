package csense.javafx.extensions.beans.property

import javafx.beans.binding.LongExpression
import kotlin.reflect.KProperty

operator fun <T> LongExpression.getValue(from: T, property: KProperty<*>): Long = value
