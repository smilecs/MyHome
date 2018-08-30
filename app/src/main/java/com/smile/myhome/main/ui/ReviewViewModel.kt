package com.smile.myhome.main.ui

import android.arch.lifecycle.ViewModel
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.repo.ArticleDataSource
import com.smile.myhome.main.repo.ArticleRepository

class ReviewViewModel : ViewModel() {
    val articleList = mutableListOf<Article>()
    private val articleRepo: ArticleDataSource by lazy {
        ArticleRepository()
    }

    fun getReviewedArticles() = articleRepo.getReviewedArticles()

}