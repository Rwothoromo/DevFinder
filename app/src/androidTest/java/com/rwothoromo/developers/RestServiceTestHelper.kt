package com.rwothoromo.developers

import android.content.Context

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Rest Service test helper.
 */
object RestServiceTestHelper {

    /**
     * Read from an input stream and return a string.
     *
     * @param inputStream the input
     * @return String the output
     * @throws Exception if fails
     */
    @Throws(Exception::class)
    fun convertStreamToString(inputStream: InputStream): String {

        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = null
        while ({ line = reader.readLine(); line }() != null) {
            stringBuilder.append(line).append('\n')
        }
        reader.close()
        return stringBuilder.toString()
    }

    /**
     * Open and return dtring from a file.
     *
     * @param context the context
     * @param filePath the input file path
     * @return String the output
     * @throws Exception if fails
     */
    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {

        val inputStream = context.resources.assets.open(filePath)

        val outStream = convertStreamToString(inputStream)
        inputStream.close()
        return outStream
    }
}

