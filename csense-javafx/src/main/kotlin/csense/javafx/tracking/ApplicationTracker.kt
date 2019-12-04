package csense.javafx.tracking

import csense.kotlin.extensions.collections.secondLastOrNull

class ApplicationTracker : BaseTracker<BaseApplicationTrackingEvents>(
        BaseApplicationTrackingEvents.Initialized
)

fun ApplicationTracker.onCreatedSplash() = logEvent(BaseApplicationTrackingEvents.CreatedSplash)
fun ApplicationTracker.onLoadingController() = logEvent(BaseApplicationTrackingEvents.LoadingController)
fun ApplicationTracker.onReady() = logEvent(BaseApplicationTrackingEvents.Ready)
fun ApplicationTracker.onClosing() = logEvent(BaseApplicationTrackingEvents.Closing)

enum class BaseApplicationTrackingEvents {
    Initialized,
    CreatedSplash,
    LoadingController,
    Ready,
    Closing
}

fun List<TrackingEvent<BaseApplicationTrackingEvents>>.logTimingString(): String {
    val last = lastOrNull() ?: return ""
    val secondLast = secondLastOrNull() ?: return ""
    val timing = (last.atTime - secondLast.atTime).toMilliSeconds()
    return when (last.event) {
        BaseApplicationTrackingEvents.Initialized -> ""
        BaseApplicationTrackingEvents.CreatedSplash -> "Took $timing to create splash"
        BaseApplicationTrackingEvents.LoadingController -> "Took $timing from splash to loading"
        BaseApplicationTrackingEvents.Ready -> "Ready took $timing"
        BaseApplicationTrackingEvents.Closing -> "Closing"
    }
}
