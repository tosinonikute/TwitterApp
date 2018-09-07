package com.example1.twitterapp.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example1.twitterapp.R
import com.example1.twitterapp.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.model.User
import com.example1.twitterapp.ui.list.ListAdapter
import com.example1.twitterapp.util.ImageUtil
import com.example1.twitterapp.util.NetworkUtil
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    private val TAG: String = "${DetailActivity::class.qualifiedName}"
    private var userId : Int = -1;
    private lateinit var imageView : ImageView
    private lateinit var name : TextView
    private lateinit var button : Button

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getBundleData()
        initViews()
        loadView()

        share()
    }

    fun initViews(){
        name = findViewById(R.id.username_detail) as TextView
        button = findViewById(R.id.share) as Button
        imageView = findViewById(R.id.img_tweet_detail) as ImageView
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

    override fun loadView(){
        if(userId != -1) {
            if(NetworkUtil.isConnected(applicationContext)){
                viewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]
                viewModel.init(userId)
                viewModel.user!!.observe(this, Observer<User> { myuser: User? ->
                    // Update views
                    displayUserInfo(myuser!!)
                })

            } else {
                displayedFailedConnection()
            }
        }
    }

    fun displayUserInfo(user: User){
        name.text = user.name
        val image = imageView;
        ImageUtil.displayImage(imageView.context, user.profileImageUrl!!, image,
                R.drawable.place_holder)
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
