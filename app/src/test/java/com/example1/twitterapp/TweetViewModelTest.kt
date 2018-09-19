package com.example1.twitterapp

import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import com.example1.twitterapp.ui.list.TweetsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


class TweetViewModelTest {

    @Mock
    lateinit var tweetsViewModel: TweetsViewModel

    @Mock
    lateinit var tweetsRepository: TweetsRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        tweetsViewModel = Mockito.spy<TweetsViewModel>(TweetsViewModel(tweetsRepository))
    }

    @Test
    fun getCallFetchTweets() {

        // Trigger
        tweetsViewModel.init()

        // Validation
        Mockito.verify<TweetsRepository>(tweetsRepository, Mockito.times(1))
                .fetchTweets(tweetsViewModel)
    }




}