package com.example1.twitterapp.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.drawable.Drawable
import android.support.annotation.VisibleForTesting
import android.util.Log
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class DetailViewModel // Instructs Dagger 2 to provide the TweetsRepository data.
@Inject
constructor(private val tweetsRepo: TweetsRepository) : ViewModel() {

    var user = MutableLiveData<User>()

    var apiError = MutableLiveData<Throwable>()

    fun getUser(userId: Int) {
        tweetsRepo.fetchUser(
                userId,
                {
                    user.value = it
                },

                {
                    apiError.value = it
                })
    }

}