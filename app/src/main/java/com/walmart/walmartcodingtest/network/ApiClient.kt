package com.walmart.walmartcodingtest.network

import com.google.gson.GsonBuilder
import com.walmart.walmartcodingtest.BuildConfig
import com.walmart.walmartcodingtest.R
import com.walmart.walmartcodingtest.app.WalmartApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private val webServices: WebServices
    private val okHttpClient: OkHttpClient

    init {

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // used to print logs
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(logging)
        }

        okHttpClient = okHttpBuilder
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(WalmartApp.instance.getString(R.string.base_url))
            .client(okHttpClient)
            .build()

        webServices = retrofit.create(WebServices::class.java)
    }

    fun getService(): WebServices = webServices
}

