package com.example1.twitterapp

import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import com.example1.twitterapp.ui.list.TweetsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback

class RepoTest{

    @Mock
    lateinit var twitterService: TwitterService

    @Mock
    lateinit var tweetsRepositoryImpl: TweetsRepositoryImpl

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        tweetsRepositoryImpl = Mockito.spy<TweetsRepositoryImpl>(TweetsRepositoryImpl(twitterService))
    }

    @SuppressWarnings("unchecked")
    @Test
    fun fetchRepos() {
        val call = Mockito.mock(Call::class.java);
        Mockito.doReturn(call).`when`<TwitterService>(twitterService).listTweets()
        //Mockito.doNothing().`when`<Call>(call).enqueue(Mockito.any(Callback::class.java))

        val tweetsRepositoryCallback = object : TweetsRepositoryImpl.TweetsRepositoryCallback {
            override fun handleTweetsError(error: Throwable) {

            }
        }

        // trigger
        tweetsRepositoryImpl.fetchTweets(tweetsRepositoryCallback)
        // validation
        Mockito.verify<TwitterService>(twitterService, Mockito.times(1)).listTweets()
    }
}