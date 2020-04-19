package csense.javafx.css

import csense.kotlin.Function1
import javafx.beans.property.Property
import javafx.css.Styleable
import javafx.css.StyleableProperty
import javafx.css.converter.BooleanConverter

class CssMetaDataBooleanPropertyBound<CallerClass : Styleable, out PropertyBoundType>(
        propertyName: String,
        initialValue: Boolean,
        propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaDataPropertyBound<CallerClass, Boolean, PropertyBoundType>(
        propertyName,
        BooleanConverter.getInstance(),
        initialValue,
        propertyAccessor
) where PropertyBoundType : Property<Boolean>, PropertyBoundType : StyleableProperty<Boolean>