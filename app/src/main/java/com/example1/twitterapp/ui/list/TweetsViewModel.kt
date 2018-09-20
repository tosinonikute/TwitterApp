package com.example1.twitterapp.ui.list

import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.repository.TweetsRepository


class TweetsViewModel // Instructs Dagger 2 to provide the TweetsRepository data.
@Inject
constructor(private val tweetsRepo: TweetsRepository) : ViewModel() {

    var tweets = MutableLiveData<List<Tweets>>()

    var isLoading = MutableLiveData<Boolean>()

    var apiError = MutableLiveData<Throwable>()

    fun getTweets() {
        isLoading.value = true
        tweetsRepo.fetchTweets(
                {
                    tweets.value = it
                    isLoading.value = false
                },

                {
                    apiError.value = it
                    isLoading.value = false
                })
    }

}