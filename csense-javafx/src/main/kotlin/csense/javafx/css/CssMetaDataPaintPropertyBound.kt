package csense.javafx.css

import csense.kotlin.Function1
import javafx.beans.property.Property
import javafx.css.Styleable
import javafx.css.StyleableProperty
import javafx.css.converter.PaintConverter
import javafx.scene.paint.Paint

class CssMetaDataPaintPropertyBound<CallerClass : Styleable, out PropertyBoundType>(
        propertyName: String,
        initialValue: Paint,
        propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaDataPropertyBound<CallerClass, Paint, PropertyBoundType>(
        propertyName,
        PaintConverter.getInstance(),
        initialValue,
        propertyAccessor
) where PropertyBoundType : Property<Paint>, PropertyBoundType : StyleableProperty<Paint>