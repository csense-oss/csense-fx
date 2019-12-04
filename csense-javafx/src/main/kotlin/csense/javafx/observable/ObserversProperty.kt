package csense.javafx.observable

import csense.kotlin.extensions.collections.*
import kotlin.properties.*
import kotlin.reflect.*

//TODO csense kotlin ?

/**
 *
 */
typealias ObserverFunction<T> = (newValue: T) -> Unit

/**
 *
 */
typealias ObserverFunctionOldNew<T> = (old: T, new: T) -> Unit

/**
 *
 */
open class ObserversProperty<T>(initialValue: T) : ReadWriteProperty<ObserversProperty<T>, T> {
    private val observers: MutableList<ObserverFunction<T>> = mutableListOf()
    private var realValue: T = initialValue

    override fun getValue(
            thisRef: ObserversProperty<T>,
            property: KProperty<*>
    ): T {
        return thisRef.realValue
    }

    override fun setValue(
            thisRef: ObserversProperty<T>,
            property: KProperty<*>,
            value: T) {
        thisRef.realValue = value
        notifyCurrent()
    }

    fun setValue(value: T) {
        this.realValue = value
        notifyCurrent()
    }

    private fun notifyCurrent() {
        observers.invokeEachWith(realValue)
    }

    fun addObserver(obs: ObserverFunction<T>) {
        observers += obs
    }

    fun removeObserver(obs: ObserverFunction<T>) {
        observers -= obs
    }
    //TODO scoped observer ?
    //single obs ?
    //x obs ?
}

/**
 * Add observer fluently
 * @receiver Me
 * @param obs Function1<[@kotlin.ParameterName] T, Unit>
 * @return Me
 */
fun <T, Me : ObserversProperty<T>> Me.addObserverF(obs: ObserverFunction<T>): Me {
    addObserver(obs)
    return this
}

/**
 * Remove observer fluently
 * @receiver Me
 * @param obs Function1<[@kotlin.ParameterName] T, Unit>
 * @return Me
 */
fun <T, Me : ObserversProperty<T>> Me.removeObserverF(obs: ObserverFunction<T>): Me {
    removeObserver(obs)
    return this
}

