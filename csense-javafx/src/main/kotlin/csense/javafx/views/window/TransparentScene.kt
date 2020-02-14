package csense.javafx.views.window

import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.SceneAntialiasing
import javafx.scene.paint.Color
import javafx.scene.paint.Paint

class TransparentScene : Scene {
    constructor(root: Parent) : super(root)
    constructor(root: Parent, width: Double, height: Double) : super(root, width, height)
    constructor(root: Parent, fill: Paint?) : super(root, fill)
    constructor(root: Parent, width: Double, height: Double, fill: Paint?) : super(root, width, height, fill)
    constructor(root: Parent, width: Double, height: Double, depthBuffer: Boolean) : super(
        root,
        width,
        height,
        depthBuffer
    )

    constructor(
        root: Parent,
        width: Double,
        height: Double,
        depthBuffer: Boolean,
        antiAliasing: SceneAntialiasing?
    ) : super(root, width, height, depthBuffer, antiAliasing)


    init {
        fill = Color.TRANSPARENT
    }

}