package csense.javafx.extensions.scene.shape

import javafx.beans.property.BooleanProperty
import javafx.beans.property.DoubleProperty
import javafx.beans.property.ObjectProperty
import javafx.scene.paint.Paint
import javafx.scene.shape.Shape
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType

inline val Shape.strokeTypeProperty: ObjectProperty<StrokeType>
    inline get() = strokeTypeProperty()

inline val Shape.strokeWidthProperty: DoubleProperty
    inline get() = strokeWidthProperty()

inline val Shape.strokeLineJoinProperty: ObjectProperty<StrokeLineJoin>
    inline get() = strokeLineJoinProperty()

inline val Shape.strokeLineCapProperty: ObjectProperty<StrokeLineCap>
    inline get() = strokeLineCapProperty()

inline val Shape.strokeMiterLimitProperty: DoubleProperty
    inline get() = strokeMiterLimitProperty()

inline val Shape.strokeDashOffsetProperty: DoubleProperty
    inline get() = strokeDashOffsetProperty()

inline val Shape.fillProperty: ObjectProperty<Paint>
    inline get() = fillProperty()

inline val Shape.strokeProperty: ObjectProperty<Paint>
    inline get() = strokeProperty()

inline val Shape.smoothProperty: BooleanProperty
    inline get() = smoothProperty()
