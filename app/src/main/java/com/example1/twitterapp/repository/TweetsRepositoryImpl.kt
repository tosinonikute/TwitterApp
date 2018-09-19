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

    override fun fetchTweets(callback: TweetsRepositoryCallback): LiveData<List<Tweets>> {
        val data = MutableLiveData<List<Tweets>>()
        twitterService.listTweets().enqueue(object: Callback<List<Tweets>> {
            override fun onResponse(call: Call<List<Tweets>>, response: Response<List<Tweets>>){
                response.body()?.let{
                    data.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Tweets>>, t: Throwable){
                callback.handleTweetsError(t)
            }
        });
        return data
    }

    override fun fetchUser(userId: Int, callback: UserRepositoryCallback) {
        twitterService.listSingleUser(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>){
                response.body()?.let{
                    callback.handleUsersResponse(response)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable){
                callback.handleUsersError(t)
            }
        });
    }

    interface TweetsRepositoryCallback {
        fun handleTweetsError(error: Throwable)
    }

    interface UserRepositoryCallback {
        fun handleUsersResponse(response: Response<User>)

        fun handleUsersError(error: Throwable)
    }
}