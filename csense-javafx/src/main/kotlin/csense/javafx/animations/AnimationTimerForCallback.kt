package csense.javafx.animations

import csense.kotlin.EmptyFunction
import javafx.animation.AnimationTimer

class AnimationTimerForCallback(val callback: EmptyFunction) : AnimationTimer() {
    override fun handle(now: Long) {
        callback()
    }
}
