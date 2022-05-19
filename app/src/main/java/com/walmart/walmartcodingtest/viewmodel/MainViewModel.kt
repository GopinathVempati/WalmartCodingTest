package com.walmart.walmartcodingtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walmart.walmartcodingtest.data.CountriesListResponse
import com.walmart.walmartcodingtest.repo.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val countriesListResponse = MutableLiveData<List<CountriesListResponse>>()
    val errorMessage = MutableLiveData<String>()

    /*To fetch NYCList using network call*/
    fun getNYCList() {
        val response = repository.getCountriesList()

        response.enqueue(object : Callback<List<CountriesListResponse>> {
            override fun onResponse(
                call: Call<List<CountriesListResponse>>,
                response: Response<List<CountriesListResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        countriesListResponse.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<CountriesListResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
