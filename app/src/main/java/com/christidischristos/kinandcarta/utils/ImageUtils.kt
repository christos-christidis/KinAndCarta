package com.christidischristos.kinandcarta.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.christidischristos.kinandcarta.R

fun loadImageFromUrl(context: Context, url: String, imageView: ImageView) {
    val progressDrawable = CircularProgressDrawable(context).apply { start() }
    Glide.with(context)
        .load(url)
        .placeholder(progressDrawable)
        .error(R.drawable.ic_offline)
        .into(imageView)
}
