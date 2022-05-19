package com.walmart.walmartcodingtest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.loading_layout.*

open class BaseActivity : AppCompatActivity() {

    fun showProgressBar() {
        progressLayout?.visibility = View.VISIBLE
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun hideProgressBar() {
        progressLayout?.visibility = View.GONE
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun apiCall(
        defaultMsg: String = getString(R.string.required_internet),
        networkFun: () -> Unit,
    ) {
        if (isNetworkAvailable()) {
            networkFun()
        } else {
            Toast.makeText(this@BaseActivity, "", Toast.LENGTH_SHORT).show()
        }
    }

    inline fun networkCheck(networkFun: () -> Unit, offlineCallback: () -> Unit) {
        if (isNetworkAvailable()) {
            networkFun()
        } else {
            offlineCallback()
        }
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}