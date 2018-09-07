package com.example1.twitterapp.ui.list

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example1.twitterapp.R
import com.example1.twitterapp.data.RestClient
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.ui.base.BaseActivity
import com.example1.twitterapp.util.NetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ImageView
import android.widget.ProgressBar
import com.example1.twitterapp.BaseApplication
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepository
import com.example1.twitterapp.util.ImageUtil
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : BaseActivity(), LifecycleOwner {

    private val TAG: String = "${MainActivity::class.qualifiedName}"
    private var progressBar: ProgressBar? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayout: LinearLayout
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter
    private var profilePic: ImageView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : TweetsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize views
        initViews()

        // load content
        loadView()
    }

    fun displayTweets(tweetList: List<Tweets>){
        hideProgress()
        adapter = ListAdapter(applicationContext, tweetList.toMutableList())
        recyclerView.adapter = adapter

        val user = tweetList.get(0).user
        setProfilePic(user?.profileImageUrl!!)

        if(tweetList.isNotEmpty()) {
            setUserBG(tweetList.get(0).user?.profileBackgroundImageUrl.toString())
        }
    }

    fun initViews(){
        recyclerView = findViewById(R.id.recycler_tweets) as RecyclerView
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        linearLayout = findViewById(R.id.background_user) as LinearLayout
        profilePic = findViewById(R.id.profile_picture) as ImageView
        progressBar = findViewById(R.id.progressbar) as ProgressBar
    }

    override fun loadView(){
        if(NetworkUtil.isConnected(applicationContext)){

            showProgress()
            viewModel = ViewModelProviders.of(this, viewModelFactory)[TweetsViewModel::class.java]
            viewModel.init()
            viewModel.tweets!!.observe(this, Observer<List<Tweets>> { tweetList: List<Tweets>? ->
                // Update views
                displayTweets(tweetList!!)
            })

        } else {
            displayedFailedConnection()
        }
    }

    fun setUserBG(bgSource: String){
        ImageUtil.displayImageDrawable(
                applicationContext, bgSource, linearLayout)
    }

    fun setProfilePic(profilePicUrl: String){
        ImageUtil.displayImage(
                applicationContext, profilePicUrl, profilePic!!, R.drawable.place_holder)
    }

    fun showProgress(){
        progressBar?.setVisibility(View.VISIBLE)
    }

    fun hideProgress(){
        progressBar?.setVisibility(View.GONE)
    }
}
