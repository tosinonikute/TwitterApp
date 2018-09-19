package com.example1.twitterapp.ui.list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example1.twitterapp.R
import com.example1.twitterapp.model.Tweets
import com.example1.twitterapp.ui.detail.DetailActivity
import com.example1.twitterapp.util.ImageUtil

class ListAdapter(val context: Context, val mItem: MutableList<Tweets>?): RecyclerView.Adapter<TweetsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsViewHolder {
        return TweetsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tweet_list, parent, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(@NonNull holder: TweetsViewHolder, position: Int) {
        val model = mItem!![holder.adapterPosition]

        holder.apply {
            this.name.text = model.user?.name
            this.description.text  = model.user?.description
            ImageUtil.displayImage(holder.imageView.context, model.user?.profileImageUrl!!, this.imageView,
                    R.drawable.place_holder)
        }

        holder.itemView.setOnClickListener { v->
            val context = v.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("user_id", model.id?.toInt())
            val activity = v.context as Activity
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int{
        return mItem!!.size
    }
}