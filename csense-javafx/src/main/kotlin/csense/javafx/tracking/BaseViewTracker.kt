package csense.javafx.tracking

import csense.kotlin.units.TimeUnit

class BaseViewTracker : BaseTracker<BaseViewTrackingEvents>(BaseViewTrackingEvents.Initialized)

//TODO WIP
enum class BaseViewTrackingEvents {
    Initialized,
    Started,
    Ready,
    Closing,
    Closed
}

fun BaseViewTracker.onStart() = logEvent(BaseViewTrackingEvents.Started)
fun BaseViewTracker.onReady() = logEvent(BaseViewTrackingEvents.Ready)
fun BaseViewTracker.onClosing() = logEvent(BaseViewTrackingEvents.Closing)
fun BaseViewTracker.onClosed() = logEvent(BaseViewTrackingEvents.Closed)

fun List<TrackingEvent<BaseViewTrackingEvents>>.logTimingString(): String {
    val last = lastOrNull() ?: return ""
    //todo use second last from csense when available.
    val secondLast = elementAtOrNull(size - 2) ?: return ""
    //TODO use cense 0.0.23 when available to not convert back and forth.
    val timing = TimeUnit.NanoSeconds(
            last.atTime.value - secondLast.atTime.value).toMilliSeconds()
    return when (last.event) {
        BaseViewTrackingEvents.Initialized -> ""
        BaseViewTrackingEvents.Started -> "Took $timing to start"
        BaseViewTrackingEvents.Ready -> "Took $timing to be ready (ui + loaded)"
        BaseViewTrackingEvents.Closing -> "Closing"
        BaseViewTrackingEvents.Closed -> "Closed"
    }
}
