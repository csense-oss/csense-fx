package csense.javafx.css

import csense.kotlin.Function1
import javafx.beans.property.Property
import javafx.css.Styleable
import javafx.css.StyleableProperty
import javafx.css.converter.SizeConverter

class CssMetaDataNumberPropertyBound<CallerClass : Styleable, out PropertyBoundType>(
        propertyName: String,
        initialValue: Number,
        propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaDataPropertyBound<CallerClass, Number, PropertyBoundType>(
        propertyName,
        SizeConverter.getInstance(),
        initialValue,
        propertyAccessor
) where PropertyBoundType : Property<Number>, PropertyBoundType : StyleableProperty<Number>