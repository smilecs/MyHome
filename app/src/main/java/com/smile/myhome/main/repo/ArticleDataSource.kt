package com.smile.myhome.main.repo

import android.arch.lifecycle.LiveData
import com.past3.ketro.model.Wrapper
import com.smile.myhome.main.model.Article

interface ArticleDataSource {
    fun getArticles(source: Int):
            LiveData<Wrapper<List<Article>>>

    fun addArticles(article: List<Article>)

    fun saveReviewedArticle(article: Article)

    fun getReviewedArticles(): LiveData<Wrapper<List<Article>>>

    fun clearData()
}