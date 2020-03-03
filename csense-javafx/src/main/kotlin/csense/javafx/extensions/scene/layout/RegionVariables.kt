@file:Suppress("unused")

package csense.javafx.extensions.scene.layout

import javafx.beans.property.*
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import javafx.scene.layout.Region
import javafx.scene.shape.Shape


inline val Region.snapToPixelProperty: BooleanProperty
    inline get() = snapToPixelProperty()

inline val Region.paddingProperty: ObjectProperty<Insets>
    inline get() = paddingProperty()


inline val Region.backgroundProperty: ObjectProperty<Background>
    inline get() = backgroundProperty()


inline val Region.borderProperty: ObjectProperty<Border>
    inline get() = borderProperty()


inline val Region.opaqueInsetsProperty: ObjectProperty<Insets>
    inline get() = opaqueInsetsProperty()


inline val Region.insetsProperty: ReadOnlyObjectProperty<Insets>
    inline get() = insetsProperty()


inline val Region.widthProperty: ReadOnlyDoubleProperty
    inline get() = widthProperty()


inline val Region.heightProperty: ReadOnlyDoubleProperty
    inline get() = heightProperty()


inline val Region.minWidthProperty: DoubleProperty
    inline get() = minWidthProperty()


inline val Region.minHeightProperty: DoubleProperty
    inline get() = minHeightProperty()


inline val Region.prefWidthProperty: DoubleProperty
    inline get() = prefWidthProperty()


inline val Region.prefHeightProperty: DoubleProperty
    inline get() = prefHeightProperty()


inline val Region.maxWidthProperty: DoubleProperty
    inline get() = maxWidthProperty()


inline val Region.maxHeightProperty: DoubleProperty
    inline get() = maxHeightProperty()


inline val Region.shapeProperty: ObjectProperty<Shape>
    inline get() = shapeProperty()


inline val Region.scaleShapeProperty: BooleanProperty
    inline get() = scaleShapeProperty()


inline val Region.centerShapeProperty: BooleanProperty
    inline get() = centerShapeProperty()


inline val Region.cacheShapeProperty: BooleanProperty
    inline get() = cacheShapeProperty()
