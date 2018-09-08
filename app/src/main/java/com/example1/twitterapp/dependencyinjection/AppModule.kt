package com.example1.twitterapp.dependencyinjection

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.example1.twitterapp.BaseApplication
import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.factory.ViewModelModule
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl


@Module(includes = arrayOf(ViewModelModule::class, NetworkModule::class))
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application : BaseApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRepository(twitterService: TwitterService): TweetsRepository {
        return TweetsRepositoryImpl(twitterService)
    }
}