package com.walmart.walmartcodingtest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.walmart.walmartcodingtest.databinding.ActivityMainBinding
import com.walmart.walmartcodingtest.network.ApiClient
import com.walmart.walmartcodingtest.network.NetworkResponse
import com.walmart.walmartcodingtest.repo.MainRepository
import com.walmart.walmartcodingtest.utils.Extensions.showSnackBar
import com.walmart.walmartcodingtest.viewmodel.MainViewModel
import com.walmart.walmartcodingtest.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = ApiClient.getService()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository((retrofitService))))[MainViewModel::class.java]

        binding.countryListTv.adapter = adapter

        viewModel.countriesListResponse.observe(this) {
            hideProgressBar()
            when (it) {
                is NetworkResponse.Success -> {
                    if (it.data.isSuccessful) {
                        it.data.body()?.apply {
                            adapter.updateData(this)
                        }
                    }
                }
                is NetworkResponse.Error -> {
                    showSnackBar(this@MainActivity,handleException(it.exception))
                }
            }
        }

        viewModel.errorMessage.observe(this) {
            hideProgressBar()
            showSnackBar(this@MainActivity,it)
        }

        apiCall {
            showProgressBar()
            viewModel.getCountriesList()
        }
    }
}