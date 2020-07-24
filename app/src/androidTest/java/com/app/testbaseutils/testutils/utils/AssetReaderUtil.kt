package com.app.testbaseutils.testutils.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object AssetReaderUtil {

    /***
     * Retrieves a file from the `source/main/assets/json_test_responses/[assetPath]`.
     */
    fun asset(context: Context, assetPath: String): String {
        try {
            val inputStream = context.assets.open("json_test_responses/$assetPath")
            return inputStreamToString(
                inputStream,
                "UTF-8"
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}