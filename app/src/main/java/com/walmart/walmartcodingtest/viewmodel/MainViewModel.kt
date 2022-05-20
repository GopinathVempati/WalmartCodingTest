package com.walmart.walmartcodingtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmart.walmartcodingtest.R
import com.walmart.walmartcodingtest.data.CountriesListResponse
import com.walmart.walmartcodingtest.network.NetworkResponse
import com.walmart.walmartcodingtest.repo.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    private val _countriesListResponse: MutableLiveData<NetworkResponse<Response<List<CountriesListResponse>>>> =
        MutableLiveData()

    val countriesListResponse: LiveData<NetworkResponse<Response<List<CountriesListResponse>>>> =
        _countriesListResponse

    /*To fetch NYCList using network call*/
    fun getCountriesList() {
        viewModelScope.launch {
            val response = repository.getCountriesList()
            _countriesListResponse.postValue(NetworkResponse.Success(response))
//            response.enqueue(object : Callback<List<CountriesListResponse>> {
//                override fun onResponse(
//                    call: Call<List<CountriesListResponse>>,
//                    response: Response<List<CountriesListResponse>>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            countriesListResponse.postValue(it)
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<List<CountriesListResponse>>, t: Throwable) {
//                    errorMessage.postValue(t.message)
//                }
//            })

        }
    }


    private var coroutineExceptionHandler =
        CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
            _countriesListResponse.postValue(NetworkResponse.Error(IOException(throwable)))
        }
}
