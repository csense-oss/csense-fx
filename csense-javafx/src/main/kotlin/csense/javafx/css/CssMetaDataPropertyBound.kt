package csense.javafx.css

import csense.javafx.extensions.beans.property.*
import javafx.beans.property.Property
import javafx.css.CssMetaData
import javafx.css.StyleConverter
import javafx.css.Styleable
import javafx.css.StyleableProperty

/**
 * Simple css metadata class that exposes the isNot bound and the styleable property via simple functional references, which can be
 * method references (so super effective) and is not an anonymous class
 * @param CallerClass : Styleable
 * @param ValueType the value type
 * @param PropertyBoundType the property type
 * @property propertyAccessor Function1<CallerClass, PropertyBoundType>
 * @constructor
 */

open class CssMetaDataPropertyBound<CallerClass : Styleable, ValueType, out PropertyBoundType>(
        property: String,
        converter: StyleConverter<*, ValueType>,
        initialValue: ValueType,
        val propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaData<CallerClass, ValueType>(
        property, converter, initialValue
) where PropertyBoundType : Property<ValueType>, PropertyBoundType : StyleableProperty<ValueType> {

    override fun isSettable(styleable: CallerClass): Boolean {
        return propertyAccessor(styleable).isNotBound
    }

    override fun getStyleableProperty(styleable: CallerClass): StyleableProperty<ValueType> {
        return propertyAccessor(styleable)
    }
}