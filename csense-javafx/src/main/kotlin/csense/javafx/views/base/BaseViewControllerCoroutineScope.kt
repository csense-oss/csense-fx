package csense.javafx.views.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface BaseViewControllerCoroutineScope : CoroutineScope {
    fun onDestroyScope()
}

class BaseViewCoroutineScopeImpl : BaseViewControllerCoroutineScope {

    private val job = SupervisorJob()

    override fun onDestroyScope() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}

