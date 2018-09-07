package com.example1.twitterapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.data.RestClient
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TweetsRepositoryImpl() : TweetsRepository {

    override fun fetchTweets(): LiveData<List<Tweets>> {
        val data = MutableLiveData<List<Tweets>>()
        RestClient.instance!!.listTweets().enqueue(object: Callback<List<Tweets>> {
            override fun onResponse(call: Call<List<Tweets>>, response: Response<List<Tweets>>){
                data.setValue(response.body())
            }

            override fun onFailure(call: Call<List<Tweets>>, t: Throwable){
                Log.e("error", t.localizedMessage)
            }
        });
        return data
    }

    override fun fetchUser(userId: Int): LiveData<User> {
        val data = MutableLiveData<User>()
        RestClient.instance!!.listSingleUser(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>){
                data.setValue(response.body())
            }

            override fun onFailure(call: Call<User>, t: Throwable){
                Log.e("error", t.localizedMessage)
            }
        });
        return data
    }
}