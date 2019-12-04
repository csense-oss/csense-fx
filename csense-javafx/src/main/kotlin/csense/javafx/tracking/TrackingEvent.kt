package csense.javafx.tracking

import csense.kotlin.units.*


data class TrackingEvent<T>(val atTime: TimeUnit.NanoSeconds, val event: T) {
    companion object
}

/**
 * Creates a new event with the current nanotime
 * @receiver TrackingEvent.Companion
 * @param event EventType
 * @return TrackingEvent
 */
fun <T> TrackingEvent.Companion.now(event: T): TrackingEvent<T> =
        TrackingEvent(TimeUnit.NanoSeconds(System.nanoTime()), event)
