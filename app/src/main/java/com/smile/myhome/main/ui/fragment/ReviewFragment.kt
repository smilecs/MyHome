package com.smile.myhome.main.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.*
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.review_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_switch_layout) {
            switchLayout()
            switchIcon(item)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchLayout() {
        if (gridLayoutManager.spanCount == ReviewAdapter.GRID_VIEW) {
            gridLayoutManager.spanCount = ReviewAdapter.LINEAR_VIEW
        } else {
            gridLayoutManager.spanCount = ReviewAdapter.GRID_VIEW
        }
        reviewAdapter.notifyItemRangeChanged(0, reviewAdapter.itemCount)
    }

    private fun switchIcon(item: MenuItem) {
        if (gridLayoutManager.spanCount == ReviewAdapter.GRID_VIEW) {
            item.icon = resources.getDrawable(R.drawable.span_linear)
        } else {
            item.icon = resources.getDrawable(R.drawable.span_grid)
        }
    }

    private fun observerListData() {
        viewModel.getReviewedArticles().observe(this, Observer { data ->
            data?.data?.let {
                viewModel.articleList.clear()
                viewModel.articleList.addAll(it)
                reviewAdapter.notifyDataSetChanged()
            }
        })
    }
}
