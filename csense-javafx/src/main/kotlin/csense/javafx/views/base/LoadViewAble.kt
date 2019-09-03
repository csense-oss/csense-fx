package csense.javafx.views.base

import javafx.scene.Parent


abstract class LoadViewAble<out RootType : Parent>(
    //forces the init to require something that the user cannot create, thus the instantiation requires outside
    @Suppress("UNUSED_PARAMETER") onViewSetup: OnViewSetup
) {
    abstract val root: RootType
}
