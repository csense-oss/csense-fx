package csense.javafx.views.base

import csense.kotlin.annotations.properties.PropertyMustBeConstant
import csense.kotlin.annotations.threading.InUi
import csense.kotlin.annotations.threading.InUiContext
import javafx.scene.Parent

@InUiContext
interface BaseView<out RootType : Parent> {
    @InUi
    @PropertyMustBeConstant
    val root: RootType
}

@InUiContext
interface BaseViewParent : BaseView<Parent>