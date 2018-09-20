package com.example1.twitterapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TweetsRepositoryImpl(var twitterService: TwitterService) : TweetsRepository {

    override fun fetchTweets(successHandler: (List<Tweets>?) -> Unit, failureHandler: (Throwable?) -> Unit) {
        twitterService.listTweets().enqueue(object: Callback<List<Tweets>> {
            override fun onResponse(call: Call<List<Tweets>>, response: Response<List<Tweets>>){
                response.body()?.let{
                    successHandler(it)
                }
            }

            override fun onFailure(call: Call<List<Tweets>>, t: Throwable){
                failureHandler(t)
            }
        });
    }

    override fun fetchUser(userId: Int, successHandler: (User?) -> Unit, failureHandler: (Throwable?) -> Unit) {
        twitterService.listSingleUser(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>){
                response.body()?.let{
                    successHandler(it)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable){
                failureHandler(t)
            }
        });
    }

}