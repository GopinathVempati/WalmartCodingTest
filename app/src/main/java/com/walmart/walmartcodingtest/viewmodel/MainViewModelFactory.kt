package com.walmart.walmartcodingtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walmart.walmartcodingtest.repo.MainRepository


class MainViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {
    /*
    We can not create ViewModel on our own.
    We need ViewModelProviders utility provided by Android to create ViewModels
    But ViewModelProviders can only instantiate ViewModels with no arg constructor*/

    /**
     * Creates a new instance of the given `Class`.
     *
     * @param modelClass a `Class` whose instance is requested
     * @return a newly created ViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Not found viewmodel")
        }
    }


}