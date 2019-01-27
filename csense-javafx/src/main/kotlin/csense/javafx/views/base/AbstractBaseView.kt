package csense.javafx.views.base

import csense.javafx.extensions.asyncDefault
import csense.javafx.extensions.asyncMain
import csense.javafx.extensions.launchDefault
import csense.javafx.extensions.launchMain
import csense.javafx.extensions.parent.addToBack
import csense.javafx.views.*
import csense.javafx.views.data.*
import csense.kotlin.logger.L
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.stage.Window
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope


abstract class AbstractBaseView<ViewBinding : Parent> :
    ToUi<ViewBinding>,
    ToBackground<ViewBinding> {

    private var currentStage: Stage? = null
    private var currentWindow: Window? = null

    internal val coroutineScope = BaseViewCoroutineScopeImpl()

    internal abstract fun getViewAsync(): Deferred<ViewBinding>

    //region inUi state transfer
    override fun inUi(action: InUiUpdateEmptyScope<ViewBinding>): Job = coroutineScope.launchMain {
        action(InUiUpdateEmpty(currentWindow, currentStage, getViewAsync().await(), this@AbstractBaseView))
    }

    final override fun <T> inUi(
        input: T,
        action: InUiUpdateInputScope<ViewBinding, T>
    ): Job = coroutineScope.launchMain {
        println("loading view ${this@AbstractBaseView}")
        val view = getViewAsync().await()
        println("got view ${this@AbstractBaseView}")
        action(InUiUpdateInput(currentWindow, currentStage, input, view, this@AbstractBaseView))
    }

    final override fun <Output> inUiAsync(
        action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateEmpty(currentWindow, currentStage, view, this@AbstractBaseView))
    }

    final override fun <Input, Output> inUiAsync(
        input: Input,
        action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> = coroutineScope.asyncMain {
        val view = getViewAsync().await()
        action(InUiUpdateInput(currentWindow, currentStage, input, view, this@AbstractBaseView))
    }
    //endregion


    //region OnBackground
    final override fun inBackground(action: InBackgroundEmptyScope<ViewBinding>): Job =
        coroutineScope.launchDefault {
            coroutineScope {
                try {
                    action(InBackgroundEmpty(this@AbstractBaseView, this))
                } catch (e: Exception) {
                    L.error("tag", "test", e)
                }
            }
        }

    final override fun <Input> inBackground(
        input: Input,
        action: InBackgroundInputScope<ViewBinding, Input>
    ): Job =
        coroutineScope.launchDefault {

        }

    final override fun <Output> inBackgroundAsync(action: InBackgroundOutputScope<ViewBinding, Output>): Deferred<Output> =
        coroutineScope.asyncDefault {
            action(InBackgroundEmpty(this@AbstractBaseView, coroutineScope))
        }

    final override fun <Input, Output> inBackgroundAsync(
        input: Input,
        action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output> =
        coroutineScope.asyncDefault {
            action(InBackgroundInput(input, this@AbstractBaseView))
        }

    open fun presentModal(ownerWindow: Window? = null) = inUi(ownerWindow) {
        val newStage = Stage().apply {
            scene = Scene(this@inUi.binding)
        }
        newStage.show()
        this@AbstractBaseView.currentStage = newStage
        this@AbstractBaseView.currentWindow = newStage
        start()
    }

    open fun addToView(toPlaceIn: Pane) = inUi(toPlaceIn) {
        input.addToBack(binding)
        this@AbstractBaseView.currentWindow = input.scene?.window
        this@AbstractBaseView.currentStage = this@AbstractBaseView.currentWindow as? Stage
        start()
    }

    open fun start() {

    }

}