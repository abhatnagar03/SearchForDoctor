package com.vivy.test.searchmydoctor.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    private static final String EXCEPTION = "Exception: ";

    private FileUtils() {
    }

    public static String createStringFromInputStream(InputStream inp) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(inp, "UTF-8"));
            StringBuilder buffer = new StringBuilder();
            buffer = appendLine(buffer, reader);
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, EXCEPTION + e.getMessage(), e);
        }
        return null;
    }

    private static StringBuilder appendLine(StringBuilder buffer, BufferedReader reader) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, EXCEPTION + e.getMessage(), e);
        }
        return buffer;
    }
}
