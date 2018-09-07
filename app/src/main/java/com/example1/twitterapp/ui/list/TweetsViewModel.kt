package com.example1.twitterapp.ui.list

import android.arch.lifecycle.LiveData
import javax.inject.Inject
import android.arch.lifecycle.ViewModel
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.repository.TweetsRepository


class TweetsViewModel // Instructs Dagger 2 to provide the TweetsRepository data.
@Inject
constructor(private val tweetsRepo: TweetsRepository) : ViewModel() {

    var tweets: LiveData<List<Tweets>>? = null
        private set

    fun init() {
        if (this.tweets != null) {
            return
        }
        tweets = tweetsRepo.fetchTweets()
    }

}