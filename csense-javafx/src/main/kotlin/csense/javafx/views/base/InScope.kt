package csense.javafx.views.base

import javafx.scene.Parent
import javafx.stage.Stage
import javafx.stage.Window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class InScope

//region States and their content


//region InUI states
/**
 * No data into
 * @param ViewBinding
 * @property binding ViewBinding
 * @constructor
 */
open class InUiUpdateEmpty<ViewBinding : LoadViewAble<out Parent>>(
    val currentWindow: Window?,
    val currentStage: Stage?,
    val binding: ViewBinding,
    private val toBackground: ToBackground<ViewBinding>
) :
    ToBackground<ViewBinding> by toBackground

/**
 * data into
 * @param ViewBinding
 * @param Input
 * @property binding ViewBinding
 * @property input Input
 * @constructor
 */
open class InUiUpdateInput<ViewBinding : LoadViewAble<out Parent>, Input>(
    currentWindow: Window?,
    currentStage: Stage?,
    val input: Input,
    binding: ViewBinding,
    toBackground: ToBackground<ViewBinding>
) : InUiUpdateEmpty<ViewBinding>(currentWindow, currentStage, binding, toBackground)

//endregion

/**
 * Share the ability to prohibit the use of "binding"
 * @param ViewBinding
 * @property binding ViewBinding
 */
interface ProhibitedBinding<ViewBinding> {
    @Deprecated(
        "Accessing view on Background is prohibited, call InUi to access it",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("inUi{ binding }", "csense.javafx.views")
    )
    val binding: ViewBinding
        get() = throw Exception("Not allowed.")
}

//region InBackground states
class InBackgroundEmpty<ViewBinding : LoadViewAble<out Parent>>(
    private val toUI: ToUi<ViewBinding>,
    val scope: CoroutineScope

) : ToUi<ViewBinding> by toUI,
    ProhibitedBinding<ViewBinding>

class InBackgroundInput<ViewBinding : LoadViewAble<out Parent>, Input>(
    val input: Input,
    private val toUI: ToUi<ViewBinding>
) :
    ToUi<ViewBinding> by toUI, ProhibitedBinding<ViewBinding>

//endregion


//endregion

//region Describes state transitions
/**
 * All to UI state transfers
 * @param ViewBinding
 */
interface ToUi<ViewBinding : LoadViewAble<out Parent>> {
    /**
     * When you want to do something with the ui with no input (could be a simple side effect)
     * @param action FunctionUnit<InUiUpdateEmpty<ViewBinding>>
     * @return Job
     */
    fun inUi(action: InUiUpdateEmptyScope<ViewBinding>): Job

    /**
     * When you want to do something with the ui with with input (display something)
     * @param input T
     * @param action InUiUpdateInputScope<ViewBinding, T>
     * @return Job
     */
    fun <Input> inUi(
        input: Input,
        action: InUiUpdateInputScope<ViewBinding, Input>
    ): Job

    /**
     * When you want to do something with the ui with with input (display something)
     * and return a result (like the text from a field or alike)
     * @param input T
     * @param action InUiUpdateInputScope<ViewBinding, T>
     * @return Job
     */
    fun <Input, Output> inUiAsync(
        input: Input,
        action: InUiUpdateInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output>

    fun <Output> inUiAsync(
        action: InUiUpdateOutputScope<ViewBinding, Output>
    ): Deferred<Output>
}

/**
 * All to background state transfers
 * @param ViewBinding
 */
interface ToBackground<ViewBinding : LoadViewAble<out Parent>> {
    /**
     * Useful for doing a side effect but nothing "computing" like taking or retrieving anything
     * @param action FunctionUnit<InBackgroundEmpty>
     * @return Job
     */
    fun inBackground(action: InBackgroundEmptyScope<ViewBinding>): Job

    /**
     *
     * @param action InBackgroundInput<ViewBinding, Input>
     * @return Job
     */
    fun <Input> inBackground(input: Input, action: InBackgroundInputScope<ViewBinding, Input>): Job

    /**
     *
     * @param action InBackgroundOutput<ViewBinding, Output>
     * @return Deferred<Output>
     */
    fun <Output> inBackgroundAsync(action: InBackgroundOutputScope<ViewBinding, Output>): Deferred<Output>

    /**
     *
     * @param action InBackgroundInputOutput<ViewBinding, Input, Output>
     * @return Job
     */
    fun <Input, Output> inBackgroundAsync(
        input: Input,
        action: InBackgroundInputOutputScope<ViewBinding, Input, Output>
    ): Deferred<Output>
}
//endregion