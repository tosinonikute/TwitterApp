package com.example1.twitterapp.data

import com.example1.twitterapp.BuildConfig
import com.example1.twitterapp.api.TwitterService
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


class RestClient{
    companion object {

        private var restClient: TwitterService? = null;
        private val BASE_URL = BuildConfig.BASE_URL

        val instance: TwitterService?
            get(){
                if(restClient == null){
                    setupInstance()
                }
                return restClient
            }

        private fun setupInstance(){

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            restClient = retrofit.create<TwitterService>(TwitterService::class.java)
        }
    }
}