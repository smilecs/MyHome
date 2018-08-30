package com.smile.myhome.main.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smile.myhome.R
import com.smile.myhome.main.ui.ReviewViewModel
import com.smile.myhome.main.ui.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.content_review.*
import kotlin.properties.Delegates

class ReviewFragment : Fragment() {
    private var gridLayoutManager: GridLayoutManager by Delegates.notNull()
    private var reviewAdapter: ReviewAdapter by Delegates.notNull()
    private var viewModel: ReviewViewModel by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ReviewViewModel::class.java)
        gridLayoutManager = GridLayoutManager(context, ReviewAdapter.LINEAR_VIEW)
        reviewAdapter = ReviewAdapter(viewModel.articleList, Glide.with(this), gridLayoutManager)
        return inflater.inflate(R.layout.content_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = reviewAdapter
        }
        observerListData()
    }

    private fun observerListData() {
        viewModel.getReviewedArticles().observe(this, Observer { data ->
            data?.data?.let {
                viewModel.articleList.clear()
                viewModel.articleList.addAll(it)
                reviewAdapter.notifyDataSetChanged()
                layoutButtonControls()
            }
        })
    }

    private fun setLinearView() {
        gridLayoutManager.spanCount = ReviewAdapter.LINEAR_VIEW
        reviewAdapter.notifyItemRangeChanged(0, reviewAdapter.itemCount)
    }

    private fun setGridView() {
        gridLayoutManager.spanCount = ReviewAdapter.GRID_VIEW
        reviewAdapter.notifyItemRangeChanged(0, reviewAdapter.itemCount)
    }

    private fun layoutButtonControls() {
        listButton.setOnClickListener {
            setLinearView()
        }
        gridButton.setOnClickListener {
            setGridView()
        }
    }
}
