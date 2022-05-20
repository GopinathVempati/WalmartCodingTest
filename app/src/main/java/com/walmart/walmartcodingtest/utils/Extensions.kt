package com.walmart.walmartcodingtest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.walmart.walmartcodingtest.R

object Extensions {
    fun View.setGone() {
        visibility = View.GONE
    }

    fun View.setVisible() {
        visibility = View.VISIBLE
    }

    fun String?.checkNull(value: String = ""): String {
        return if (this.isNullOrEmpty()) value else this
    }

    fun Activity.showSnackBar(activity: Context?, message: String?) {
        if (activity != null && activity is Activity && message != null) {
            var view = activity.findViewById<View>(R.id.main)
            if (view == null) view = activity.findViewById(android.R.id.content)
            val snackbar = Snackbar.make(view!!, message, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}