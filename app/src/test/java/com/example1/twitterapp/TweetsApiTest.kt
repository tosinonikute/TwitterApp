package com.example1.twitterapp


import com.example1.twitterapp.api.TwitterService
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import com.example1.twitterapp.ui.list.TweetsViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call

class TweetsApiTest {

    @Mock
    internal var twitterService: TwitterService? = null

    val alltweets = getAllTweets()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @Throws(Exception::class)
    fun testAPIResponse() {

        val name = "WizeBot"
        val profileImageUrl = "\"http://pbs.twimg.com/profile_images/655037238409990144/VrE-PK8l_normal.png"
        val profileBackgroundImageUrl = "http://abs.twimg.com/images/themes/theme1/bg.png\""


        // assert for name
        Assert.assertEquals(name, alltweets.user!!.name)

        // assert for background image url
        Assert.assertEquals(profileBackgroundImageUrl, alltweets.user!!.profileBackgroundImageUrl)

        // assert for profile image
        Assert.assertEquals(profileImageUrl, alltweets.user!!.profileImageUrl)

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

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }
}