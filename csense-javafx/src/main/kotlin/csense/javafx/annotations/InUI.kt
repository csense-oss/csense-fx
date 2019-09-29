package csense.javafx.annotations

/**
 * Specifies that the given code REQUIRES the UI thread to run.
 * Can be used for statical analysis to help user avoid crossThreading issues.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class InUI