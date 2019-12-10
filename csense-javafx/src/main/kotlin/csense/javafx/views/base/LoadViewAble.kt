package csense.javafx.views.base

import csense.kotlin.annotations.threading.InUi
import csense.kotlin.annotations.threading.InUiContext
import javafx.scene.Parent

@InUiContext
abstract class LoadViewAble<out RootType : Parent>(
        //forces the init to require something that the user cannot create, thus the instantiation requires outside
        @Suppress("UNUSED_PARAMETER") onViewSetup: OnViewSetup
) {
    @InUi
    abstract val root: RootType
}

abstract class LoadViewAbleParent(@Suppress("UNUSED_PARAMETER") onViewSetup: OnViewSetup)
    : LoadViewAble<Parent>(onViewSetup)