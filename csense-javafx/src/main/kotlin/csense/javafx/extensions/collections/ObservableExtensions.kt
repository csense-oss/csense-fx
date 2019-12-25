package csense.javafx.extensions.collections

import javafx.collections.FXCollections
import javafx.collections.ObservableList

fun <E> List<E>.toObservable(): ObservableList<E> =
        FXCollections.observableList(this)

fun <E> observableListOf(vararg elements: E): ObservableList<E> =
        FXCollections.observableArrayList(elements.toList())

fun <E> Array<E>.toObservable(): ObservableList<E> =
        FXCollections.observableArrayList(this.toList())
