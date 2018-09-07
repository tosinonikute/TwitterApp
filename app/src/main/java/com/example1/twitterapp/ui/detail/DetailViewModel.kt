package com.example1.twitterapp.ui.detail

import android.arch.lifecycle.LiveData
import javax.inject.Inject
import android.arch.lifecycle.ViewModel
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository


class DetailViewModel // Instructs Dagger 2 to provide the TweetsRepository data.
@Inject
constructor(private val tweetsRepo: TweetsRepository) : ViewModel() {

    var user: LiveData<User>? = null
        private set

    fun init(userId: Int) {
        if (this.user != null) {
            return
        }
        user = tweetsRepo.fetchUser(userId)
    }
}