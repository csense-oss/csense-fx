package csense.javafx.extensions.beans.property

import javafx.beans.binding.IntegerExpression
import kotlin.reflect.KProperty

operator fun <T> IntegerExpression.getValue(from: T, property: KProperty<*>): Int = value
