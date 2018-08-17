package com.example.rwothoromo.developers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Rest Service test helper.
 */
public class RestServiceTestHelper {

    /**
     * Read from an input stream and return a string.
     *
     * @param inputStream the input
     * @return String the output
     * @throws Exception if fails
     */
    public static String convertStreamToString(InputStream inputStream) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        reader.close();
        return stringBuilder.toString();
    }

    /**
     * Open and return dtring from a file.
     *
     * @param context the context
     * @param filePath the input file path
     * @return String the output
     * @throws Exception if fails
     */
    public static String getStringFromFile(Context context, String filePath) throws Exception {

        final InputStream inputStream = context.getResources().getAssets().open(filePath);

        String outStream = convertStreamToString(inputStream);
        inputStream.close();
        return outStream;
    }
}

