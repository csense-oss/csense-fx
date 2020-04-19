package csense.javafx.css

import csense.kotlin.Function1
import javafx.beans.property.Property
import javafx.css.Styleable
import javafx.css.StyleableProperty
import javafx.css.converter.ColorConverter
import javafx.scene.paint.Color

class CssMetaDataColorPropertyBound<CallerClass : Styleable, out PropertyBoundType>(
        propertyName: String,
        initialValue: Color,
        propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaDataPropertyBound<CallerClass, Color, PropertyBoundType>(
        propertyName,
        ColorConverter.getInstance(),
        initialValue,
        propertyAccessor
) where PropertyBoundType : Property<Color>, PropertyBoundType : StyleableProperty<Color>