package csense.javafx.extensions.beans.property

import javafx.beans.binding.SetExpression
import kotlin.reflect.KProperty

operator fun <T,E> SetExpression<E>.getValue(from: T, property: KProperty<*>): Set<E> = value
