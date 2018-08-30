package com.smile.myhome.main.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.smile.myhome.main.model.Article
import kotlinx.android.synthetic.main.review_grid_item.view.*
import kotlinx.android.synthetic.main.review_item_list.view.*

sealed class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract val articleImage: ImageView
    abstract fun bind(requestManager: RequestManager, model: Article)
}

class GridReviewHolder(val view: View) : ReviewViewHolder(view) {
    override val articleImage: ImageView = view.reviewedImage
    override fun bind(requestManager: RequestManager, model: Article) {
        requestManager.load(model.media[0].uri).into(articleImage)
    }
}

class LinearReviewHolder(val view: View) : ReviewViewHolder(view) {
    override val articleImage: ImageView = view.reviewListImage
    val titleText = view.articleTitle
    override fun bind(requestManager: RequestManager, model: Article) {
        requestManager.load(model.media[0].uri).into(articleImage)
        titleText.text = model.title
    }
}