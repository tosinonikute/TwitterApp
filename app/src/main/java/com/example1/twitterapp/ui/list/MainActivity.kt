package com.example1.twitterapp.ui.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example1.twitterapp.R
import com.example1.twitterapp.data.RestClient
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.ui.base.BaseActivity
import com.example1.twitterapp.util.NetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.example1.twitterapp.util.ImageUtil


class MainActivity : BaseActivity() {

    /**
     * I plan to implement view model to save states when pulling from the network
     * I plan to use Dependency Injection with Viewmodel as well
     * For now, i will be using config Changes in Manifest
     * * Important: I also plan to write Unit Test to test the API and Viewmodel, I will send my updated codes *
     *
     * Reason: No Enough Time
     */

    //private val TAG: String = "${MainActivity::class.qualifiedName}"
    private val TAG: String = "MainActivity"

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayout: LinearLayout
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        loadView()
    }

    fun initViews(){
        recyclerView = findViewById(R.id.recycler_tweets) as RecyclerView
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        linearLayout = findViewById(R.id.background_user) as LinearLayout

    }

    override fun loadView(){
        if(NetworkUtil.isConnected(applicationContext)){

            loadTeets()

        } else {
            displayedFailedConnection()
        }
    }

    fun loadTeets(){

        RestClient.instance!!.listTweets().enqueue(object: Callback<List<Tweets>> {
            override fun onResponse(call: Call<List<Tweets>>, reponse: Response<List<Tweets>>){

                if(reponse.code() == 200){
                    val tweets = reponse.body()
                    Log.d(TAG, tweets!!.get(0).user!!.name.toString())
                    adapter = ListAdapter(applicationContext, tweets.toMutableList())
                    recyclerView.adapter = adapter

                    if(tweets.isNotEmpty()) {
                        setUserBG(tweets.get(0).user?.profileBackgroundImageUrl.toString())
                    }

                } else {
                    //
                }
            }

            override fun onFailure(call: Call<List<Tweets>>, t: Throwable){

            }
        });

    }

    fun setUserBG(bgSource: String){
        ImageUtil
                .displayImageDrawable(
                        applicationContext,
                        bgSource,
                        linearLayout
                )
    }
}
