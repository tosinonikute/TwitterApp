package com.example1.twitterapp.repository

import android.arch.lifecycle.LiveData
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User

interface TweetsRepository {

    fun fetchTweets(successHandler: (List<Tweets>?) -> Unit, failureHandler: (Throwable?) -> Unit)

    fun fetchUser(userId: Int, successHandler: (User?) -> Unit, failureHandler: (Throwable?) -> Unit)
}