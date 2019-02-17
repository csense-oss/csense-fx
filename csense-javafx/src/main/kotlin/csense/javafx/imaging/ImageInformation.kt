package csense.javafx.imaging

import csense.kotlin.extensions.map
import java.io.File
import java.io.IOException
import java.io.InputStream


object ImageInformation {

    fun readFileMetaData(file: File) {

    }

    @Throws(IOException::class)
    private fun readInt(`is`: InputStream, noOfBytes: Int, bigEndian: Boolean): Int {
        var ret = 0
        var sv = bigEndian.map((noOfBytes - 1) * 8, 0)
        val cnt = bigEndian.map(-8, 8)

        for (i in 0 until noOfBytes) {
            ret = ret or (`is`.read() shl sv)
            sv += cnt
        }
        return ret
    }
}

private sealed class ImageInformationReader {
    object PDF : ImageInformationReader()

}

/**
 *
 * @property height Int
 * @property width Int
 * @property mimeType String
 * @constructor
 */
data class ImageMetaInformation(val height: Int, val width: Int, val mimeType: String)