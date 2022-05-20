package com.walmart.walmartcodingtest.network

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

sealed class NetworkResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: Exception) : NetworkResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
