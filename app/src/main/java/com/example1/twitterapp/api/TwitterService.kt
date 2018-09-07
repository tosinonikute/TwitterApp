package com.example1.twitterapp.api

import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    @GET("/api/statuses/user_timeline")
    fun listTweets(): Call<List<Tweets>>

    @GET("/api/statuses/user_timeline")
    fun listTweetsSample(): Call<Tweets>

    @GET("/api/user")
    fun listSingleUser(@Query("id") user_id: Int): Call<User>
}