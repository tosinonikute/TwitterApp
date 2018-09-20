package com.example1.twitterapp.ui.list

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example1.twitterapp.R
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.ui.base.BaseActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example1.twitterapp.model.User
import com.example1.twitterapp.util.ImageUtil
import javax.inject.Inject


class MainActivity : BaseActivity(), LifecycleOwner {

    private val TAG: String = "${MainActivity::class.qualifiedName}"
    private var progressBar: ProgressBar? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayout: LinearLayout
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter
    private lateinit var  profilePic: ImageView
    private lateinit var  userName: TextView
    private lateinit var  userDescription: TextView

    private lateinit var viewModel: TweetsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[TweetsViewModel::class.java]

        // initialize views
        initViews()

        // load content
        attachObserver()
    }

    fun displayTweets(tweetList: List<Tweets>){
        adapter = ListAdapter(applicationContext, tweetList.toMutableList())
        recyclerView.adapter = adapter

        val user = tweetList.get(0).user
        setProfilePic(user?.profileImageUrl!!)

        if(tweetList.isNotEmpty()) {
            setUserInfo(tweetList.get(0).user!!)
        }
    }

    fun initViews(){
        recyclerView = findViewById(R.id.recycler_tweets) as RecyclerView
        linearLayoutManager = LinearLayoutManager(applicationContext)

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = linearLayoutManager
        }

        linearLayout = findViewById(R.id.background_user) as LinearLayout
        profilePic = findViewById(R.id.profile_picture) as ImageView
        progressBar = findViewById(R.id.progressbar) as ProgressBar
        userName = findViewById(R.id.user_name) as TextView
        userDescription = findViewById(R.id.user_description) as TextView
    }

    private fun attachObserver() {
        viewModel.getTweets()
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.apply {
                showLoadingDialog(it)
            }
        })
        viewModel.apiError.observe(this, Observer<Throwable> {
            it?.apply {
                handleApiError(it)
            }
        })
        viewModel.tweets.observe(this, Observer<List<Tweets>> { tweetList: List<Tweets>? ->
            // Update views
            displayTweets(tweetList!!)
        })
    }

    override fun loadView(){
        attachObserver()
    }

    fun setUserInfo(userInfo: User){
        userInfo.let {
            userName.text = it.name
            userDescription.text = it.description
            ImageUtil.displayImageDrawable(
                    applicationContext, it.profileBackgroundImageUrl.toString(), linearLayout)
        }
    }

    fun setProfilePic(profilePicUrl: String){
        ImageUtil.displayImage(
                applicationContext, profilePicUrl, profilePic!!, R.drawable.place_holder)
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) progressBar?.visibility = View.VISIBLE else progressBar?.visibility = View.GONE
    }
}
