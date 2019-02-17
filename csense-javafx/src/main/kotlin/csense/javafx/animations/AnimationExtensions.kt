package csense.javafx.animations

import csense.kotlin.AsyncFunction1
import csense.kotlin.extensions.coroutines.launchMain
import javafx.scene.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job


/**
 *
 * @receiver Node
 * @param animationFunction AsyncFunction1<Node, Unit>
 * @param scope CoroutineScope
 * @return Job
 */
fun Node.animate(
    scope: CoroutineScope = GlobalScope,
    animationFunction: AsyncFunction1<Node, Unit>
): Job = scope.launchMain {
    animationFunction(this@animate)
}
