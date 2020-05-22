package csense.javafx.extensions.scene.text

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.coroutines.*
import javafx.scene.text.*
import kotlinx.coroutines.*
import java.io.*
import java.net.*

object FontExtensions {
    
    
    /**
     *
     * @param fontsUrls Array<out URL>
     * @return Int
     */
    @IntLimit(from = 0)
    fun loadFonts(vararg fontsUrls: URL): Int = fontsUrls.sumBy {
        loadFont(it).map(1, 0)
    }
    
    fun loadFontsAsync(scope: CoroutineScope, vararg fontUrls: URL): List<Deferred<Boolean>> = fontUrls.map {
        scope.asyncIO {
            loadFont(it)
        }
    }
    
    /**
     *
     * @param fontUrl URL
     * @return Boolean
     */
    fun loadFont(fontUrl: URL): Boolean {
        return try {
            fontUrl.openStream().use {
                Font.loadFont(it.buffered(), 0.0)
            }
            true
        } catch (e: IOException) {
            false
        }
    }
}

fun Font.withSize(@DoubleLimitFromMin(from = 0.0) newSize: Double): Font {
    //todo style... / full name here...
    return Font.font(name, newSize)
}