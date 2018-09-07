package com.example1.twitterapp.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example1.twitterapp.ui.detail.DetailViewModel
import com.example1.twitterapp.ui.list.TweetsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TweetsViewModel::class)
    abstract fun bindTweetActivityViewModel(mainActivityViewModel: TweetsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailActivityViewModel(detailActivityViewModel: DetailViewModel) : ViewModel

}