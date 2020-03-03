@file:Suppress("unused")

package csense.javafx.extensions.scene

import javafx.beans.property.*
import javafx.event.EventDispatcher
import javafx.event.EventHandler
import javafx.geometry.Bounds
import javafx.geometry.NodeOrientation
import javafx.geometry.Point3D
import javafx.scene.*
import javafx.scene.effect.Effect
import javafx.scene.input.*
import javafx.scene.transform.Transform

/**
 * Contains accessors for the properties in a node, to simply kotlin usage
 */



inline val Node.parentProperty: ReadOnlyObjectProperty<Parent>
    inline get() = parentProperty()

inline val Node.sceneProperty: ReadOnlyObjectProperty<Scene>
    inline get() = sceneProperty()

inline val Node.idProperty: StringProperty
    inline get() = idProperty()

inline val Node.styleProperty: StringProperty
    inline get() = styleProperty()

inline val Node.visibleProperty: BooleanProperty
    inline get() = visibleProperty()

inline val Node.cursorProperty: ObjectProperty<Cursor>
    inline get() = cursorProperty()

inline val Node.opacityProperty: DoubleProperty
    inline get() = opacityProperty()

inline val Node.clipProperty: ObjectProperty<Node>
    inline get() = clipProperty()

inline val Node.cacheHintProperty: ObjectProperty<CacheHint>
    inline get() = cacheHintProperty()

inline val Node.effectProperty: ObjectProperty<Effect>
    inline get() = effectProperty()

inline val Node.depthTestProperty: ObjectProperty<DepthTest>
    inline get() = depthTestProperty()

inline val Node.disableProperty: BooleanProperty
    inline get() = disableProperty()

inline val Node.pickOnBoundsProperty: BooleanProperty
    inline get() = pickOnBoundsProperty()


inline val Node.disabledProperty: ReadOnlyBooleanProperty
    inline get() = disabledProperty()


inline val Node.onDragEnteredProperty: ObjectProperty<EventHandler<in DragEvent>>
    inline get() = onDragEnteredProperty()


inline val Node.onDragExitedProperty: ObjectProperty<EventHandler<in DragEvent>>
    inline get() = onDragExitedProperty()


inline val Node.onDragOverProperty: ObjectProperty<EventHandler<in DragEvent>>
    inline get() = onDragOverProperty()

inline val Node.onDragDroppedProperty: ObjectProperty<EventHandler<in DragEvent>>
    inline get() = onDragDroppedProperty()

inline val Node.onDragDoneProperty: ObjectProperty<EventHandler<in DragEvent>>
    inline get() = onDragDoneProperty()

inline val Node.managedProperty: BooleanProperty
    inline get() = managedProperty()

inline val Node.layoutXProperty: DoubleProperty
    inline get() = layoutXProperty()

inline val Node.layoutYProperty: DoubleProperty
    inline get() = layoutYProperty()

inline val Node.boundsInParentProperty: ReadOnlyObjectProperty<Bounds>
    inline get() = boundsInParentProperty()

inline val Node.boundsInLocalProperty: ReadOnlyObjectProperty<Bounds>
    inline get() = boundsInLocalProperty()

inline val Node.layoutBoundsProperty: ReadOnlyObjectProperty<Bounds>
    inline get() = layoutBoundsProperty()

inline val Node.viewOrderProperty: DoubleProperty
    inline get() = viewOrderProperty()

inline val Node.translateXProperty: DoubleProperty
    inline get() = translateXProperty()

inline val Node.translateYProperty: DoubleProperty
    inline get() = translateYProperty()

inline val Node.translateZProperty: DoubleProperty
    inline get() = translateZProperty()

inline val Node.scaleXProperty: DoubleProperty
    inline get() = scaleXProperty()

inline val Node.scaleYProperty: DoubleProperty
    inline get() = scaleYProperty()

inline val Node.scaleZProperty: DoubleProperty
    inline get() = scaleZProperty()

inline val Node.rotateProperty: DoubleProperty
    inline get() = rotateProperty()

inline val Node.rotationAxisProperty: ObjectProperty<Point3D>
    inline get() = rotationAxisProperty()

inline val Node.localToParentTransformProperty: ReadOnlyObjectProperty<Transform>
    inline get() = localToParentTransformProperty()

inline val Node.localToSceneTransformProperty: ReadOnlyObjectProperty<Transform>
    inline get() = localToSceneTransformProperty()

inline val Node.nodeOrientationProperty: ObjectProperty<NodeOrientation>
    inline get() = nodeOrientationProperty()

inline val Node.effectiveNodeOrientationProperty: ReadOnlyObjectProperty<NodeOrientation>
    inline get() = effectiveNodeOrientationProperty()

inline val Node.mouseTransparentProperty: BooleanProperty
    inline get() = mouseTransparentProperty()

inline val Node.hoverProperty: ReadOnlyBooleanProperty
    inline get() = hoverProperty()

inline val Node.pressedProperty: ReadOnlyBooleanProperty
    inline get() = pressedProperty()

inline val Node.onContextMenuRequestedProperty: ObjectProperty<EventHandler<in ContextMenuEvent>>
    inline get() = onContextMenuRequestedProperty()

inline val Node.onMouseClickedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseClickedProperty()

inline val Node.onMouseDraggedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseDraggedProperty()

inline val Node.onMouseEnteredProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseEnteredProperty()

inline val Node.onMouseExitedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseExitedProperty()

inline val Node.onMouseMovedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseMovedProperty()

inline val Node.onMousePressedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMousePressedProperty()

inline val Node.onMouseReleasedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onMouseReleasedProperty()

inline val Node.onDragDetectedProperty: ObjectProperty<EventHandler<in MouseEvent>>
    inline get() = onDragDetectedProperty()

inline val Node.onMouseDragOverProperty: ObjectProperty<EventHandler<in MouseDragEvent>>
    inline get() = onMouseDragOverProperty()

inline val Node.onMouseDragReleasedProperty: ObjectProperty<EventHandler<in MouseDragEvent>>
    inline get() = onMouseDragReleasedProperty()

inline val Node.onMouseDragEnteredProperty: ObjectProperty<EventHandler<in MouseDragEvent>>
    inline get() = onMouseDragEnteredProperty()

inline val Node.onMouseDragExitedProperty: ObjectProperty<EventHandler<in MouseDragEvent>>
    inline get() = onMouseDragExitedProperty()

inline val Node.onScrollStartedProperty: ObjectProperty<EventHandler<in ScrollEvent>>
    inline get() = onScrollStartedProperty()

inline val Node.onScrollProperty: ObjectProperty<EventHandler<in ScrollEvent>>
    inline get() = onScrollProperty()

inline val Node.onScrollFinishedProperty: ObjectProperty<EventHandler<in ScrollEvent>>
    inline get() = onScrollFinishedProperty()

inline val Node.onRotationStartedProperty: ObjectProperty<EventHandler<in RotateEvent>>
    inline get() = onRotationStartedProperty()

inline val Node.onRotateProperty: ObjectProperty<EventHandler<in RotateEvent>>
    inline get() = onRotateProperty()

inline val Node.onRotationFinishedProperty: ObjectProperty<EventHandler<in RotateEvent>>
    inline get() = onRotationFinishedProperty()

inline val Node.onZoomStartedProperty: ObjectProperty<EventHandler<in ZoomEvent>>
    inline get() = onZoomStartedProperty()

inline val Node.onZoomProperty: ObjectProperty<EventHandler<in ZoomEvent>>
    inline get() = onZoomProperty()

inline val Node.onZoomFinishedProperty: ObjectProperty<EventHandler<in ZoomEvent>>
    inline get() = onZoomFinishedProperty()

inline val Node.onSwipeUpProperty: ObjectProperty<EventHandler<in SwipeEvent>>
    inline get() = onSwipeUpProperty()

inline val Node.onSwipeDownProperty: ObjectProperty<EventHandler<in SwipeEvent>>
    inline get() = onSwipeDownProperty()

inline val Node.onSwipeLeftProperty: ObjectProperty<EventHandler<in SwipeEvent>>
    inline get() = onSwipeLeftProperty()

inline val Node.onSwipeRightProperty: ObjectProperty<EventHandler<in SwipeEvent>>
    inline get() = onSwipeRightProperty()

inline val Node.onTouchPressedProperty: ObjectProperty<EventHandler<in TouchEvent>>
    inline get() = onTouchPressedProperty()

inline val Node.onTouchMovedProperty: ObjectProperty<EventHandler<in TouchEvent>>
    inline get() = onTouchMovedProperty()

inline val Node.onTouchReleasedProperty: ObjectProperty<EventHandler<in TouchEvent>>
    inline get() = onTouchReleasedProperty()

inline val Node.onTouchStationaryProperty: ObjectProperty<EventHandler<in TouchEvent>>
    inline get() = onTouchStationaryProperty()

inline val Node.onKeyPressedProperty: ObjectProperty<EventHandler<in KeyEvent>>
    inline get() = onKeyPressedProperty()

inline val Node.onKeyReleasedProperty: ObjectProperty<EventHandler<in KeyEvent>>
    inline get() = onKeyReleasedProperty()

inline val Node.onKeyTypedProperty: ObjectProperty<EventHandler<in KeyEvent>>
    inline get() = onKeyTypedProperty()

inline val Node.onInputMethodTextChangedProperty: ObjectProperty<EventHandler<in InputMethodEvent>>
    inline get() = onInputMethodTextChangedProperty()

inline val Node.inputMethodRequestsProperty: ObjectProperty<InputMethodRequests>
    inline get() = inputMethodRequestsProperty()

inline val Node.focusedProperty: ReadOnlyBooleanProperty
    inline get() = focusedProperty()

inline val Node.focusTraversableProperty: BooleanProperty
    inline get() = focusTraversableProperty()

inline val Node.eventDispatcherProperty: ObjectProperty<EventDispatcher>
    inline get() = eventDispatcherProperty()

inline val Node.accessibleRoleProperty: ObjectProperty<AccessibleRole>
    inline get() = accessibleRoleProperty()

inline val Node.accessibleRoleDescriptionProperty: ObjectProperty<String>
    inline get() = accessibleRoleDescriptionProperty()

inline val Node.accessibleTextProperty: ObjectProperty<String>
    inline get() = accessibleTextProperty()

inline val Node.accessibleHelpProperty: ObjectProperty<String>
    inline get() = accessibleHelpProperty()
