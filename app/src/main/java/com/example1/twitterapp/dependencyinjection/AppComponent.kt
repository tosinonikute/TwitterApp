package com.example1.twitterapp.dependencyinjection

import com.example1.twitterapp.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuildActivityModule::class))

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<BaseApplication>()

}