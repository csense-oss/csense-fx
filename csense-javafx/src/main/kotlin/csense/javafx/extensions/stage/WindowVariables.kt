@file:Suppress("unused")

package csense.javafx.extensions.stage

import javafx.beans.property.*
import javafx.event.EventDispatcher
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Window
import javafx.stage.WindowEvent

inline val Window.outputScaleXProperty: ReadOnlyDoubleProperty
    inline get() = outputScaleXProperty()

inline val Window.forceIntegerRenderScaleProperty: BooleanProperty
    inline get() = forceIntegerRenderScaleProperty()

inline val Window.renderScaleXProperty: DoubleProperty
    inline get() = renderScaleXProperty()

inline val Window.renderScaleYProperty: DoubleProperty
    inline get() = renderScaleYProperty()

inline val Window.xProperty: ReadOnlyDoubleProperty
    inline get() = xProperty()

inline val Window.yProperty: ReadOnlyDoubleProperty
    inline get() = yProperty()

inline val Window.widthProperty: ReadOnlyDoubleProperty
    inline get() = widthProperty()

inline val Window.heightProperty: ReadOnlyDoubleProperty
    inline get() = heightProperty()

inline val Window.focusedProperty: ReadOnlyBooleanProperty
    inline get() = focusedProperty()

inline val Window.sceneProperty: ReadOnlyObjectProperty<Scene>
    inline get() = sceneProperty()

inline val Window.opacityProperty: DoubleProperty
    inline get() = opacityProperty()

inline val Window.onCloseRequestProperty: ObjectProperty<EventHandler<WindowEvent>>
    inline get() = onCloseRequestProperty()

inline val Window.onShowingProperty: ObjectProperty<EventHandler<WindowEvent>>
    inline get() = onShowingProperty()

inline val Window.onShownProperty: ObjectProperty<EventHandler<WindowEvent>>
    inline get() = onShownProperty()

inline val Window.onHidingProperty: ObjectProperty<EventHandler<WindowEvent>>
    inline get() = onHidingProperty()

inline val Window.onHiddenProperty: ObjectProperty<EventHandler<WindowEvent>>
    inline get() = onHiddenProperty()

inline val Window.showingProperty: ReadOnlyBooleanProperty
    inline get() = showingProperty()

inline val Window.eventDispatcherProperty: ObjectProperty<EventDispatcher>
    inline get() = eventDispatcherProperty()
