package com.example1.twitterapp.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil{
    fun isConnected(context: Context): Boolean{
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivity.activeNetworkInfo != null && connectivity.activeNetworkInfo.isConnected
    }
}