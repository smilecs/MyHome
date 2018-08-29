package com.smile.myhome.main.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.past3.ketro.api.Kobserver
import com.smile.myhome.R
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.repo.ArticleRepository
import com.smile.myhome.main.ui.MainViewModel
import com.smile.myhome.main.ui.adapter.SelectionListAdapter
import kotlinx.android.synthetic.main.selection_screen.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 *
 */
class SelectionFragment : Fragment(), View.OnClickListener {
    private var listAdapter: SelectionListAdapter by Delegates.notNull()
    private var viewModel: MainViewModel by Delegates.notNull()
    private var listLayoutManager: LinearLayoutManager by Delegates.notNull()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        listAdapter = SelectionListAdapter(Glide.with(this), viewModel.articleList)
        listLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return inflater.inflate(R.layout.selection_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = listLayoutManager
            setHasFixedSize(true)
            adapter = listAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }
        likeButton.setOnClickListener(this)
        dislikeButton.setOnClickListener(this)
        getArticleData(savedInstanceState)
        observerCounter()
        toggleObserver()
    }

    private fun getArticleData(bundle: Bundle?) {
        progressBar.visibility = View.VISIBLE
        val source = if (bundle == null) ArticleRepository.REMOTE_SOURCE else ArticleRepository.LOCAL_SOURCE
        viewModel.getArticles(source).observe(this, object : Kobserver<List<Article>>() {
            override fun onException(exception: Exception) {
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
                toggleEmptyState(true)
            }

            override fun onSuccess(data: List<Article>) {
                if (!data.isEmpty()) {
                    toggleViewState()
                    listAdapter.notifyDataSetChanged()
                } else {
                    toggleEmptyState(noServerData = true)
                }
            }
        })
    }

    private fun observerCounter() {
        viewModel.counterLiveData.observe(this, Observer { data ->
            data?.let {
                articleCount.text = getString(R.string.counter_text, it, viewModel.totalSize)
            }
        })
    }

    override fun onClick(p0: View?) {
        val position = listLayoutManager.findFirstVisibleItemPosition()
        val rate = p0?.id == R.id.likeButton
        viewModel.rateArticle(rate, position)
        listAdapter.notifyItemRemoved(position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveArticles()
    }

    private fun toggleObserver() {
        viewModel.toggleLiveData.observe(this, Observer { toggle ->
            if (toggle != null && toggle) {
                reviewButton.isEnabled = true
                reviewButton.setOnClickListener {

                }
                toggleEmptyState()
            }
        })
    }

    private fun toggleEmptyState(noServerData: Boolean = false) {
        rateButtonContainer.visibility = View.GONE
        emptyViewState.visibility = View.VISIBLE
        val emptyText = if (noServerData) getString(R.string.no_server_data) else getString(R.string.empty_view_text)
        emptyViewStateText.visibility = View.VISIBLE
        emptyViewStateText.text = emptyText
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun toggleViewState() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        rateButtonContainer.visibility = View.VISIBLE
        emptyViewState.visibility = View.GONE
        emptyViewStateText.visibility = View.GONE
    }
}
