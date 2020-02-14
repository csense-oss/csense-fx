package csense.javafx.extensions.beans.property

import javafx.beans.binding.FloatExpression
import kotlin.reflect.KProperty

operator fun <T> FloatExpression.getValue(from: T, property: KProperty<*>): Float = value
