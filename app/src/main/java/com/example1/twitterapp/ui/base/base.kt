package com.example1.twitterapp.ui.base

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example1.twitterapp.R

abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun loadView()
    private var snackbarOffline: Snackbar? = null

    fun displayedFailedConnection(){
        displayOfflineSnackBar("No Connection")
    }

    fun displayOfflineSnackBar(str: String){
        snackbarOffline = Snackbar.make(findViewById(android.R.id.content), str, Snackbar.LENGTH_INDEFINITE)
        snackbarOffline!!.setAction("Retry", View.OnClickListener { loadView() })
        snackbarOffline!!.setActionTextColor(resources.getColor(R.color.colorWhite))
        snackbarOffline!!.show()
    }

}