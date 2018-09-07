package com.example1.twitterapp.dependencyinjection

import com.example1.twitterapp.ui.detail.DetailActivity
import com.example1.twitterapp.ui.list.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun detailActivity() : DetailActivity
}