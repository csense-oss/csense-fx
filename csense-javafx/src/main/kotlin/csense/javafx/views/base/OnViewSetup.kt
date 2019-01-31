package csense.javafx.views.base

/**
 * Tagging class, used to make it impossible for implementations to "recreate" the UI;
 */
class OnViewSetup private constructor() {
    companion object {
        internal val instance = OnViewSetup()
    }
}