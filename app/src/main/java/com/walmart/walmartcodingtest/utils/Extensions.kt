package com.walmart.walmartcodingtest.utils

import android.view.View

object Extensions {
    fun View.setGone() {
        visibility = View.GONE
    }

    fun View.setVisible() {
        visibility = View.VISIBLE
    }
}