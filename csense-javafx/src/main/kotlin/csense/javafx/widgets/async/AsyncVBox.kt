package csense.javafx.widgets.async

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.label
import csense.javafx.viewdsl.textField
import csense.kotlin.Function0R
import csense.kotlin.extensions.coroutines.asyncDefault
import javafx.scene.Node
import javafx.scene.layout.VBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

// TODO experiment.
//val test = asyncVBox {
//    addViewAsync { label { text = "asd" } }
//    addViewAsync { label { text = "1234" } }
//    addViewAsync { textField {} }
//    addViewAsync { button("asd") }
//}
//
//fun asyncVBox(action: AsyncViewBuilder.() -> Unit): VBox = runBlocking {
//    val box = VBox()
//    AsyncViewBuilder(box.children, this).apply(action).build()
//    return@runBlocking box
//}
//
//class AsyncViewBuilder(
//        private val container: MutableList<Node>,
//        private val scope: CoroutineScope) {
//
//    private val creators = mutableListOf<Deferred<Node>>()
//    fun addViewAsync(creatorFunction: Function0R<Node>) {
//        creators += scope.asyncDefault {
//            creatorFunction()
//        }
//    }
//
//    suspend fun build() {
//        val views = creators.awaitAll()
//        container += views
//    }
//
//}

