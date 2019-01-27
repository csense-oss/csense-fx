package csense.javafx.viewdsl

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class InViewDsl


typealias ScopedViewDsl<T> = (@InViewDsl T).() -> Unit