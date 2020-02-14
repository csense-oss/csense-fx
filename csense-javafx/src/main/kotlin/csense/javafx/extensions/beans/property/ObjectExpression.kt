package csense.javafx.extensions.beans.property

import javafx.beans.binding.ObjectExpression
import kotlin.reflect.KProperty

operator fun <T, E> ObjectExpression<E>.getValue(from: T, property: KProperty<*>): E = value

