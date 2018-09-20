package com.example1.twitterapp.ui.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example1.twitterapp.R
import com.example1.twitterapp.util.NetworkError
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun loadView()
    private var snackbarOffline: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun displayOfflineSnackBar(str: String){
        snackbarOffline = Snackbar.make(findViewById(android.R.id.content), str, Snackbar.LENGTH_INDEFINITE)
        snackbarOffline!!.setAction("Retry", View.OnClickListener { loadView() })
        snackbarOffline!!.setActionTextColor(resources.getColor(R.color.colorWhite))
        snackbarOffline!!.show()
    }

    fun displayedFailedConnection(){
        displayOfflineSnackBar("No Connection")
    }

    fun handleApiError(error: Throwable){
        displayOfflineSnackBar(NetworkError.apiError(error))
    }
}