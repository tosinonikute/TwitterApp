package com.example1.twitterapp.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class TweetsViewModel // provide the TweetsRepository data manually in activity.
constructor(private val tweetsRepo: TweetsRepository) : ViewModel(), TweetsRepositoryImpl.TweetsRepositoryCallback {

    var tweets: LiveData<List<Tweets>>? = null
        private set

    fun init() {
        if (this.tweets != null) {
            return
        }
        tweets = tweetsRepo.fetchTweets(this)
    }

    override fun handleTweetsError(error: Throwable) {

    }

}