package csense.javafx.extensions.animation

import csense.kotlin.extensions.mapLazy
import javafx.animation.Timeline

fun Timeline.playOrPause(shouldPlay: Boolean) =
        shouldPlay.mapLazy(::play, ::pause)