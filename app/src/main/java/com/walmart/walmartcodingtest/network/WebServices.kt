package com.walmart.walmartcodingtest.network

import com.google.gson.GsonBuilder
import com.walmart.walmartcodingtest.BuildConfig
import com.walmart.walmartcodingtest.data.CountriesListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WebServices {

    @GET("countries.json")
    fun getCountriesList(): Call<List<CountriesListResponse>>

}