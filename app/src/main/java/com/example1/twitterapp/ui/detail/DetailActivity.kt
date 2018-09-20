package com.example1.twitterapp.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example1.twitterapp.R
import com.example1.twitterapp.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent
import android.widget.LinearLayout
import com.example1.twitterapp.model.User
import com.example1.twitterapp.repository.TweetsRepositoryImpl
import com.example1.twitterapp.util.ImageUtil
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    private val TAG: String = "${DetailActivity::class.qualifiedName}"
    private var userId : Int = -1;
    private lateinit var imageView : ImageView
    private lateinit var name : TextView
    private lateinit var button : Button
    private lateinit var viewModel: DetailViewModel
    private lateinit var linearLayout: LinearLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]

        getBundleData()
        initViews()
        attachObserver()
        share()
    }

    fun initViews(){
        name = findViewById(R.id.username_detail) as TextView
        button = findViewById(R.id.share) as Button
        imageView = findViewById(R.id.img_tweet_detail) as ImageView
        linearLayout = findViewById(R.id.linear_profile_banner) as LinearLayout
    }

    fun share(){
        // Assumed twitter URL and Title
        val str = "http://wizetwitter.com"
        val title = "Twit Title shared"

        button.setOnClickListener { view ->
            // share twitter
            shareLink(str, title)
        }
    }

    fun getBundleData(){
        val extras = intent.extras
        if(extras != null){
            userId = extras.getInt("user_id")
            Log.d(TAG, userId.toString())
        }
    }

    private fun attachObserver() {
        if(userId != -1) {
            viewModel.getUser(userId)

            viewModel.apiError.observe(this, Observer<Throwable> {
                it?.apply {
                    handleApiError(it)
                }
            })
            viewModel.user.observe(this, Observer<User> { user: User? ->
                // Update views
                displayUserInfo(user!!)
            })
        }
    }

    override fun loadView(){
        attachObserver()
    }

    fun displayUserInfo(user: User){
        name.text = user.name
        ImageUtil.displayImage(imageView.context, user.profileImageUrl!!, imageView, R.drawable.place_holder)
        toolbar.title = user.name
        ImageUtil.displayImageDrawable(applicationContext, user.profileBannerUrl.toString(), linearLayout)
    }

    fun shareLink(link: String, title: String) {
        //sharing implementation here
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = link
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title)
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            android.R.id.home -> {
                onBackPressed()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
