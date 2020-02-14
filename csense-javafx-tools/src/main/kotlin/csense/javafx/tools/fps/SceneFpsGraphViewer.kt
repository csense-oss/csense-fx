package csense.javafx.tools.fps

import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.InUiUpdateEmpty
import csense.javafx.views.base.BaseViewParent
import csense.kotlin.extensions.coroutines.asyncMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

class SceneFpsGraphViewerUi : BaseViewParent {
    override val root = vBox {

    }

}

class SceneFpsGraphViewer : BaseEmptyViewController<SceneFpsGraphViewerUi>() {
    override fun InUiUpdateEmpty<SceneFpsGraphViewerUi>.onReady() {

    }

    override fun CoroutineScope.createNewUi(): Deferred<SceneFpsGraphViewerUi> = asyncMain {
        SceneFpsGraphViewerUi()
    }

}