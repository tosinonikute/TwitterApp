package com.example1.twitterapp

import com.example1.twitterapp.dependencyinjection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    companion object {
        var instance: BaseApplication? = null
            private set
    }

    override fun applicationInjector() : AndroidInjector<BaseApplication> =
            DaggerAppComponent.builder().create(this@BaseApplication)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}