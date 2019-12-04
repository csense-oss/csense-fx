package csense.javafx.tracking

import csense.javafx.observable.*

abstract class BaseTracker<T>(private val initial: T) : ObserversProperty<List<TrackingEvent<T>>>(listOf()) {

    private val events: MutableList<TrackingEvent<T>> = mutableListOf(
            TrackingEvent.now(initial))

    fun logEvent(event: T) {
        if (shouldSkip(event)) {
            return
        }
        events.add(TrackingEvent.now(event))
        setValue(events)
    }

    open fun shouldSkip(event: T): Boolean {
        return event == initial
    }
}