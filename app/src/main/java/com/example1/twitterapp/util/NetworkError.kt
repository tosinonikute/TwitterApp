package com.example1.twitterapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example1.twitterapp.ui.list.MainActivity
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkError{

    private val TAG: String = "${NetworkError::class.qualifiedName}"

    fun apiError(error: Throwable): String{
        Log.d(TAG, Log.getStackTraceString(error))

        if (error is HttpException) {
            return "Network Connection Error: " + error.localizedMessage

        } else if (error is SocketTimeoutException) {
            return "Network Timeout: " + error.localizedMessage

        } else if(error is UnknownHostException) {
            return "DNS Error: " + error.localizedMessage

        } else if(error is IllegalArgumentException) {
            return "Bad URL format: " + error.localizedMessage

        } else {
            return error.localizedMessage
        }
    }
}