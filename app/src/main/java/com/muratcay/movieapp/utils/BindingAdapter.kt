package com.muratcay.movieapp.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muratcay.movieapp.R
import com.muratcay.movieapp.utils.Constants.IMAGE_URL

@BindingAdapter("status")
fun bindStatus(statusImage: AppCompatImageView, status: Status?) {
    when (status) {
        Status.LOADING -> {
            statusImage.visibility = View.VISIBLE
            statusImage.setImageResource(R.drawable.loading_animation)
        }
        Status.SUCCESS -> statusImage.visibility = View.GONE

        else -> {
            statusImage.visibility = View.VISIBLE
            statusImage.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: AppCompatImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context).load(IMAGE_URL + imageUrl).centerCrop().apply(
            RequestOptions.placeholderOf(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(imageView)
    }
}