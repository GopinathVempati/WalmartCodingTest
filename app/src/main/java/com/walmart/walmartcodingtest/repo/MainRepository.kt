package com.walmart.walmartcodingtest.repo

import com.walmart.walmartcodingtest.network.WebServices


class MainRepository constructor(private val webServices: WebServices) {
    /*A repository defines data operations.
    These operations sometimes need parameters that define how to run them */
    fun getCountriesList() = webServices.getCountriesList()
}