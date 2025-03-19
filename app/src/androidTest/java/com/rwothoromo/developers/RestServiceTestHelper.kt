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
     * @param rawJsonResource the input file path
     * @return String the output
     * @throws Exception if fails
     */
    @Throws(Exception::class)
    fun getStringFromFile(context: Context, rawJsonResource: Int): String {

        // Assuming the rawJsonResource e.g. R.raw.mock_api_200_response in the res folder under raw
        val inputStream: InputStream = context.resources.openRawResource(rawJsonResource)

        val outStream = convertStreamToString(inputStream)
        inputStream.close()
        return outStream
    }
}

