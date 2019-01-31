package csense.javafx.views

import csense.javafx.views.base.LoadViewAble
import csense.javafx.views.base.OnViewSetup
import javafx.scene.Parent

/**
 * Conceptualize a view with input and output
 * typical either a view computing a result, resolving a result, from a given input.
 * like looking into a database (with an input of what name for example)
 */
abstract class BaseViewInOutput<ViewLoad, ViewBinding : LoadViewAble<out Parent>, Din, DinTransformed, Dout>(
    input: Din,
    viewLoader: Function2<ViewLoad, OnViewSetup, ViewBinding>
) : BaseViewInput<ViewLoad, ViewBinding, Din, DinTransformed>(input, viewLoader),
    OutputViewAble<Dout>
