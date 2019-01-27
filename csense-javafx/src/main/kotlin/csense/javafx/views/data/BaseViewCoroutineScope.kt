package csense.javafx.views.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface BaseViewCoroutineScope : CoroutineScope {
    fun onDestroyScope()
}

class BaseViewCoroutineScopeImpl : BaseViewCoroutineScope {

    private val job = SupervisorJob()

    override fun onDestroyScope() {
        job.cancel()
    }

    override  val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}

