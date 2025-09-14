package com.dicoding.newsapp

import android.content.Context
import androidx.core.view.ActionProvider
import androidx.test.core.app.ApplicationProvider
import java.io.IOException
import java.io.InputStreamReader

object JsonConverter {
    fun readStringFromFile(fileName: String): String{
        try {
            val applicationaContext = ApplicationProvider.getApplicationContext<Context>()
            val inputStream = applicationaContext.assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        }catch (e: IOException){
            throw e
        }
    }
}