package csense.javafx.css

import csense.kotlin.Function1
import javafx.beans.property.Property
import javafx.css.Styleable
import javafx.css.StyleableProperty
import javafx.css.converter.EnumConverter

class CssMetaDataEnumPropertyBound<CallerClass : Styleable, ValueType : Enum<ValueType>, out PropertyBoundType>(
        propertyName: String,
        initialValue: ValueType,
        enumClass: Class<ValueType>,
        propertyAccessor: Function1<CallerClass, PropertyBoundType>
) : CssMetaDataPropertyBound<CallerClass, ValueType, PropertyBoundType>(
        propertyName,
        StyleConverterEnumCache.getInstance<ValueType>(enumClass),
        initialValue,
        propertyAccessor
) where PropertyBoundType : Property<ValueType>, PropertyBoundType : StyleableProperty<ValueType>

object StyleConverterEnumCache {
    private val cachedMap = mutableMapOf<
            Class<*>,
            EnumConverter<*>>()

    fun <E : Enum<E>> getInstance(clz: Class<E>): EnumConverter<E> {
        return (cachedMap[clz] ?: EnumConverter(clz).apply {
            cachedMap[clz] = this
        }) as EnumConverter<E>
    }
}
