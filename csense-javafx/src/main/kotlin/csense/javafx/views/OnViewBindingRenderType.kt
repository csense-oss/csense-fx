package csense.javafx.views

import csense.javafx.annotations.*
import csense.javafx.views.base.*
import javafx.scene.*


interface OnViewBindingRenderType<ViewBinding : LoadViewAble<Parent>> {
    /**
     * Hookpoint for handling special rendering
     *  of an "inline" view (akk we are just a part of something)
     * @receiver ViewBinding
     */
    @InUI
    fun ViewBinding.onRenderInline() {
        //NOP
    }

    /**
     * Hookpoint for handling special rendering
     *  of an full view (we own the whole thing).
     * @receiver ViewBinding
     */
    @InUI
    fun ViewBinding.onRenderStandAlone() {
        //NOP
    }
}

/**
 * Helper function for setting / calling the right initialization flow for OnViewBindingRenderType.
 * @receiver OnViewBindingRenderType<ViewBinding>
 * @param isInline Boolean true if we are a inline view.
 * @param binding ViewBinding the ViewBinding to use.
 */
internal fun <ViewBinding : LoadViewAble<Parent>> OnViewBindingRenderType<ViewBinding>.setupOnRender(
        isInline: Boolean,
        binding: ViewBinding
) {
    if (isInline) {
        binding.onRenderInline()
    } else {
        binding.onRenderStandAlone()
    }
}