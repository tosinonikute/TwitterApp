package com.example1.twitterapp.util

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

class ImageUtil{
    companion object {
        fun displayImage(context: Context, imageResource: String, imageView: ImageView, drawable: Int){

            val requestOptions = RequestOptions();
            requestOptions.placeholder(drawable)
            requestOptions.error(drawable)

            Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageResource).into(imageView)
        }

        fun displayImageDrawable(context: Context, bgSource: String, linearLayout: LinearLayout){
            Glide.with(context)
                    .load(bgSource).into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            linearLayout.background = resource
                        }
                    })
        }

        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            val context = imageView.getContext()
            Glide.with(context).load(url).into(imageView)
        }
    }
}