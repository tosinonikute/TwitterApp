package com.example1.twitterapp.factory

import dagger.MapKey
import kotlin.reflect.KClass
import android.arch.lifecycle.ViewModel

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)