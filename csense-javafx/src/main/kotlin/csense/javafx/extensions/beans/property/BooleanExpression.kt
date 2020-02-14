package csense.javafx.extensions.beans.property

import javafx.beans.binding.BooleanExpression
import kotlin.reflect.KProperty

operator fun <T> BooleanExpression.getValue(from: T, property: KProperty<*>): Boolean = value
