package com.smile.myhome.main.repo

import android.arch.lifecycle.LiveData
import com.past3.ketro.model.Wrapper
import com.smile.myhome.main.model.Article

interface ArticleDataSource {
    fun getArticles(source: Int):
            LiveData<Wrapper<List<Article>>>

    fun addArticle(article: List<Article>)

    fun clearData()
}