package com.example1.twitterapp.ui.list

import android.app.Activity
import android.content.Context
import android.content.Intent
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

class ListAdapter(val context: Context, val mItem: MutableList<Tweets>?): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

     class MyViewHolder(val mView: View) : RecyclerView.ViewHolder(mView){
         val imageView : ImageView
         val name : TextView
         val description : TextView

         init{
             name = mView.findViewById(R.id.username) as TextView
             description = mView.findViewById(R.id.description) as TextView
             imageView = mView.findViewById(R.id.img_tweet) as ImageView
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet_list, parent, false) as View

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = mItem!![holder.adapterPosition]

        holder.description.text = model.user!!.description
        holder.name.text = model.user.name

        ImageUtil
                .displayImage(
                        holder.imageView.context,
                        model.user.profileImageUrl!!,
                        holder.imageView,
                        R.drawable.place_holder
                )

        holder.mView.setOnClickListener { v->
            val context = v.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("user_id", model?.id)
            val activity = v.context as Activity
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int{
        return mItem!!.size
    }
}