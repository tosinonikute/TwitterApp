package com.example1.twitterapp

import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import com.example1.twitterapp.ui.detail.DetailViewModel
import com.example1.twitterapp.ui.list.TweetsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class DetailViewModelTest{

    @Mock
    lateinit var detailViewModel: DetailViewModel

    @Mock
    lateinit var tweetsRepository: TweetsRepository

    val userId = 1;

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        detailViewModel = Mockito.spy<DetailViewModel>(DetailViewModel(tweetsRepository))
    }

    @Test
    fun getCallFetchTweets() {

        // Trigger
        detailViewModel.getUser(userId)

        // Validation
        Mockito.verify<TweetsRepository>(tweetsRepository, Mockito.times(1))
                .fetchUser(userId, detailViewModel)
    }
}