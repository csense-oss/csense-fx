package csense.javafx

import csense.javafx.viewdsl.button
import csense.javafx.viewdsl.vBox
import csense.kotlin.extensions.coroutines.withContextDefault
import javafx.application.Platform
import javafx.scene.layout.VBox
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.openjdk.jmh.annotations.*
import java.util.concurrent.CountDownLatch


@Measurement(iterations = 5)
@Warmup(iterations = 5)
@Fork(value = 2, warmups = 0)
@BenchmarkMode(Mode.All)
open class AsyncViewTest {

//    companion object {
//        init{

//        }
//    }


//    @Benchmark
//    open fun vboxAsync(state: JfxState): VBox {
//        return runBlocking {
//            withContextDefault {
//                vBox {
//                    button("")
//                    button("")
//                    button("")
//                    button("")
//                    button("")
//                }
//            }
//        }
//        return asyncVBox {
//            addViewAsync { button("") }
//            addViewAsync { button("") }
//            addViewAsync { button("") }
//            addViewAsync { button("") }
//            addViewAsync { button("") }
//        }
//    }
//
//    @Benchmark
//    open fun regularVBox(state: JfxState): VBox {
//        return vBox {
//            button("")
//            button("")
//            button("")
//            button("")
//            button("")
//        }
//    }
//
//
//    @State(Scope.Benchmark)
//    open class JfxState {
//        @Setup
//        fun setupJfxThread() {
//            val latch = CountDownLatch(1)
//            Platform.startup { latch.countDown() }
//            latch.await()
//        }
//    }

}
