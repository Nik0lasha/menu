package com.example.whattocook.Utill

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.whattocook.R
import kotlinx.kotlin.synthetic.main.item_article.view.*

fun ImageView.loadFromUrl(url: String?) {

    Glide.with(this)
            .load(url)
            .error (R.drawable.placeholder)
            .into(iv_article_image)
}

fun View.isVisible(visibility: Boolean) =
    if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE