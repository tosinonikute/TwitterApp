package com.example1.twitterapp.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example1.twitterapp.R

class TweetsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView by lazy { view.findViewById(R.id.img_tweet) as ImageView }
    val name by lazy { view.findViewById(R.id.username) as TextView }
    val description by lazy { view.findViewById(R.id.description) as TextView }
}