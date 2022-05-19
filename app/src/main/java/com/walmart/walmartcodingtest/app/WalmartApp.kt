package com.walmart.walmartcodingtest.app

import android.app.Application

class WalmartApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WalmartApp
        private const val TAG = "WalmartApp"
    }
}