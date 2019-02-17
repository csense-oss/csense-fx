package csense.javafx.views.base

import javafx.scene.Parent


abstract class LoadViewAble<RootType : Parent>(@Suppress("UNUSED_PARAMETER") onViewSetup: OnViewSetup) {
    abstract val root: RootType
}
