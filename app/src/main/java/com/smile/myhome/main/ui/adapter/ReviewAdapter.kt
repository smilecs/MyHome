package com.smile.myhome.main.ui.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.smile.myhome.R
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.ui.GridReviewHolder
import com.smile.myhome.main.ui.LinearReviewHolder
import com.smile.myhome.main.ui.ReviewViewHolder

class ReviewAdapter(val reviewModel: List<Article>,
                    val reqManager: RequestManager,
                    val layoutManager: GridLayoutManager) : RecyclerView.Adapter<ReviewViewHolder>() {
    companion object {
        const val GRID_VIEW = 2
        const val LINEAR_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return when (viewType) {
            GRID_VIEW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.review_grid_item, parent, false)
                GridReviewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_list, parent, false)
                LinearReviewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount == GRID_VIEW) {
            GRID_VIEW
        } else {
            LINEAR_VIEW
        }
    }

    override fun getItemCount() = reviewModel.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reqManager, reviewModel[position])
    }
}