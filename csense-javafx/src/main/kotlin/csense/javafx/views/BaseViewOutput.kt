package csense.javafx.views

import javafx.scene.Parent
import kotlinx.coroutines.Deferred


/**
 * Conceptualize a view with only output, so no input
 */
abstract class BaseViewOutput<ViewLoad, ViewBinding : Parent, Dout> :
    BaseView<ViewLoad, ViewBinding>(), OutputViewAble<Dout>


interface OutputViewAble<Dout> {
    fun createResultAsync(): Deferred<Dout>
}