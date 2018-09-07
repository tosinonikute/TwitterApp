package com.example1.twitterapp.repository

import android.arch.lifecycle.LiveData
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User

interface TweetsRepository {

    fun fetchTweets(): LiveData<List<Tweets>>

    fun fetchUser(userId: Int): LiveData<User>
}