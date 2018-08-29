package com.smile.myhome.main.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.smile.myhome.R
import com.smile.myhome.main.model.Article
import kotlinx.android.synthetic.main.article_list_item.view.*

class SelectionListAdapter(val reqManager: RequestManager,
                           val articles: MutableList<Article>) :
        RecyclerView.Adapter<SelectionListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val model = articles[pos]
        reqManager.load(model.media[0].uri).into(holder.articleImage)

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val articleImage: ImageView by lazy {
            view.articleImage
        }
    }
}