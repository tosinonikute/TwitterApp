package com.example1.twitterapp.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.support.annotation.VisibleForTesting
import android.util.Log
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import retrofit2.Response
import java.util.*


class DetailViewModel // Instructs Dagger 2 to provide the TweetsRepository data.
constructor(private val tweetsRepo: TweetsRepository) : ViewModel(), TweetsRepositoryImpl.UserRepositoryCallback {

    var name = ObservableField<String>()

    fun getUser(userId: Int) {
        if (userId != -1) {
            tweetsRepo.fetchUser(userId, this)
        }
    }

    override fun handleUsersResponse(response: Response<User>) {
        if (response.isSuccessful()) {
            val user = response.body()
            if (user != null) {
                renderSuccess(user)
            }
        }
    }

    @VisibleForTesting
    fun renderSuccess(user: User) {
        name.set(user.name)
    }

    override fun handleUsersError(error: Throwable) {

    }

}