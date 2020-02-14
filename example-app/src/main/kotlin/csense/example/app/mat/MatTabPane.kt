package csense.example.app.mat

import csense.javafx.material.widgets.tab.MatTabPane
import csense.javafx.viewdsl.vBox
import csense.javafx.views.BaseEmptyViewController
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InUiUpdateEmpty
import csense.kotlin.extensions.coroutines.asyncMain
import javafx.scene.control.Tab
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

class MatTabPaneUi : BaseView<MatTabPane> {

    override val root: MatTabPane = MatTabPane(
            Tab("First", vBox {

            })
    )

}

class MatTabPaneViewController : BaseEmptyViewController<MatTabPaneUi>() {
    override fun InUiUpdateEmpty<MatTabPaneUi>.onReady() {
        TODO("Not yet implemented")
    }

    override fun CoroutineScope.createNewUi(): Deferred<MatTabPaneUi> = CompletableDeferred(MatTabPaneUi())

}

