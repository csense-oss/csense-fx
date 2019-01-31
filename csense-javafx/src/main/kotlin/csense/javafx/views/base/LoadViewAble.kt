package csense.javafx.views.base

import javafx.scene.Parent


abstract class LoadViewAble<RootType : Parent>(onViewSetup: OnViewSetup) {
    abstract val root: RootType
}
