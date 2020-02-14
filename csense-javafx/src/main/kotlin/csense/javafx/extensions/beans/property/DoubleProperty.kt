package csense.javafx.extensions.beans.property

import javafx.beans.binding.DoubleExpression
import kotlin.reflect.KProperty

operator fun <T> DoubleExpression.getValue(from: T, property: KProperty<*>): Double = value
