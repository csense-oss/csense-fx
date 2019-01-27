package csense.javafx.views

import javafx.scene.Parent

/**
 * Conceptualize a view with input and output
 * typical either a view computing a result, resolving a result, from a given input.
 * like looking into a database (with an input of what name for example)
 */
abstract class BaseViewInOutput<ViewLoad, ViewBinding: Parent, Din, DinTransformed, Dout>(
    input: Din
) : BaseViewInput<ViewLoad, ViewBinding, Din, DinTransformed>(input),
    OutputViewAble<Dout>