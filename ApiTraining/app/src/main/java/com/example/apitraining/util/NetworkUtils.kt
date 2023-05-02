package com.example.apitraining.util


import android.net.Uri
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException
import java.util.*

class NetworkUtils {
    companion object{
        private const val API_BASE_URL:String = "https://api.worldnewsapi.com"
        private const val TOP_GET:String = "/search-news"
        private const val TEXT:String = "text"
        private const val PARAM_ACCESS_TOKEN:String = "api-key"

        public fun generateURL(text: String): URL{
            var builtUri: Uri? = Uri.parse(API_BASE_URL + TOP_GET)
                .buildUpon()
                .appendQueryParameter(TEXT,text)
                .appendQueryParameter(
                    PARAM_ACCESS_TOKEN,
                    "41bbf0bc70df4877beb0f1cd73984659")
                .build()
            var url: URL? = null
            url = URL(builtUri.toString())

            return url
        }

        @Throws(IOException::class)
        fun getResponseFromURL(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            return try {
                val input : InputStream = urlConnection.inputStream
                val scanner = Scanner(input)
                scanner.useDelimiter("\\A")
                val hasInput = scanner.hasNext()
                if (hasInput) {
                    scanner.next()
                } else {
                    null
                }
            } catch (e: UnknownHostException){
                null
            } finally {
                urlConnection.disconnect()
            }
        }
    }

}