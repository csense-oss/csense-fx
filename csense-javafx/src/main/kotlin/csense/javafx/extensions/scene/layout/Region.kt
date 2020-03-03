package csense.javafx.extensions.scene.layout

import csense.javafx.extensions.listener.ChangeListenerNewValue
import javafx.beans.value.ChangeListener
import javafx.scene.layout.Region

typealias OnResizeCallback = (width: Number, height: Number) -> Unit

fun Region.onResize(onResizeFunction: OnResizeCallback): ResizeListener {
    val heightListener = ChangeListenerNewValue<Number> {
        onResizeFunction(width, it)
    }
    val widthListener = ChangeListenerNewValue<Number> {
        onResizeFunction(it, height)
    }
    heightProperty.addListener(heightListener)
    widthProperty.addListener(widthListener)
    return ResizeListener(heightListener, widthListener)
}

fun Region.onResizeAndNow(onResizeFunction: OnResizeCallback): ResizeListener {
    onResizeFunction(width, height)
    return onResize(onResizeFunction)
}

data class ResizeListener(val heightListener: ChangeListener<Number>, val widthListener: ChangeListener<Number>) {
    fun removeListenersFrom(region: Region) {
        region.heightProperty.removeListener(heightListener)
        region.widthProperty.removeListener(widthListener)
    }
}