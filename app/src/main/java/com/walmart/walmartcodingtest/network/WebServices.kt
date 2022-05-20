package com.walmart.walmartcodingtest.network

import com.walmart.walmartcodingtest.data.CountriesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServices {

    @GET("countries.json")
    suspend fun getCountriesList(): Response<List<CountriesListResponse>>

}