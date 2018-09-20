package com.example1.twitterapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import com.example1.twitterapp.ui.list.TweetsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TweetViewModelTest2 {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var tweetsViewModel: TweetsViewModel

    @Mock
    lateinit var tweetsRepository: TweetsRepository


    @Mock
    lateinit var twitterService: TwitterService

    @Mock
    var t: Throwable = Exception()

    @Mock
    var tweets = MutableLiveData<List<Tweets>>()
    @Mock
    var isLoading = MutableLiveData<Boolean>()
    @Mock
    var apiError = MutableLiveData<Throwable>()


    val mockedTweets = listOf(getAllTweets())
    val mockedUsers = getAllUsers()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        tweetsViewModel = TweetsViewModel(tweetsRepository).apply {
            tweetsRepository = object : TweetsRepository {
                override fun fetchTweets(successHandler: (List<Tweets>?) -> Unit, failureHandler: (Throwable?) -> Unit) {
                    successHandler(mockedTweets)
                    failureHandler(t)
                }
                override fun fetchUser(userId: Int, successHandler: (User?) -> Unit, failureHandler: (Throwable?) -> Unit) {
                    successHandler(mockedUsers)
                    failureHandler(t)
                }
            }
        }
    }

    @Test
    fun testTweets() {
        val tweets = MutableLiveData<List<Tweets>>()
        val observer = Mockito.mock(Observer::class.java)
        tweetsViewModel.tweets = tweets

        tweets.observeForever(observer as Observer<List<Tweets>>)

        tweetsViewModel.getTweets()

        Mockito.verify(observer).onChanged(mockedTweets)
        Mockito.verifyNoMoreInteractions(observer)

        // Validation
        // Mockito.verify<TweetsRepository>(tweetsRepository, Mockito.times(1)).fetchTweets()
    }

    private fun getAllTweets(): Tweets {
        // Setting up the values for test
        val tweets = Tweets()
        val newUser = User();

        newUser.profileImageUrl = "\"http://pbs.twimg.com/profile_images/655037238409990144/VrE-PK8l_normal.png"
        newUser.profileBackgroundImageUrl = "http://abs.twimg.com/images/themes/theme1/bg.png\""
        newUser.name = "WizeBot"
        tweets.user = newUser
        return tweets;
    }

    private fun getAllUsers(): User {
        val newUser = User();
        newUser.profileImageUrl = "\"http://pbs.twimg.com/profile_images/655037238409990144/VrE-PK8l_normal.png"
        newUser.profileBackgroundImageUrl = "http://abs.twimg.com/images/themes/theme1/bg.png\""
        newUser.name = "WizeBot"
        return newUser;
    }


}