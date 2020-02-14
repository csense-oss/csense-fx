package csense.javafx.extensions.beans.property

import javafx.beans.binding.StringExpression
import kotlin.reflect.KProperty

operator fun <T> StringExpression.getValue(from: T, property: KProperty<*>): String = value
