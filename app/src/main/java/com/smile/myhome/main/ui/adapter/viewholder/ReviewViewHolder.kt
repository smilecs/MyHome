package com.smile.myhome.main.ui.adapter.viewholder

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.smile.myhome.main.model.Article
import kotlinx.android.synthetic.main.review_grid_item.view.*
import kotlinx.android.synthetic.main.review_item_list.view.*

sealed class ReviewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    abstract val articleImage: ImageView
    abstract val reviewIndicator: View
    open fun bind(requestManager: RequestManager, model: Article) {
        requestManager.load(model.media[0].uri).into(articleImage)
        val colorType = if (model.liked) android.R.color.holo_green_light else android.R.color.holo_red_dark
        reviewIndicator.setBackgroundColor(ContextCompat.getColor(view.context, colorType))
    }
}

class GridReviewHolder(view: View) : ReviewViewHolder(view) {
    override val articleImage: ImageView = view.reviewedImage
    override val reviewIndicator: View = view.reviewChoiceGrid
}

class LinearReviewHolder(view: View) : ReviewViewHolder(view) {
    override val articleImage: ImageView = view.reviewListImage
    override val reviewIndicator: View = view.reviewChoiceList
    private val titleText: TextView = view.articleTitle
    override fun bind(requestManager: RequestManager, model: Article) {
        super.bind(requestManager, model)
        titleText.text = model.title
    }
}