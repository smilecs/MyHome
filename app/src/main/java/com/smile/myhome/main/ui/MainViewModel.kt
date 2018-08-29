package com.smile.myhome.main.ui

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.past3.ketro.model.Wrapper
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.repo.ArticleDataSource
import com.smile.myhome.main.repo.ArticleRepository

class MainViewModel(val app: Application) : AndroidViewModel(app) {
    val articleList = mutableListOf<Article>()
    val counterLiveData = MutableLiveData<Int>()
    val toggleLiveData = MutableLiveData<Boolean>()

    var counter: Int = 0
    private val articleRepo: ArticleDataSource by lazy {
        ArticleRepository()
    }


    fun getArticles(source: Int = ArticleRepository.REMOTE_SOURCE): LiveData<Wrapper<List<Article>>> {
        return Transformations.map(articleRepo.getArticles(source), Function {
            clearArticles()
            it.data?.let { data ->
                articleList.addAll(data)
            }
            counterLiveData.value = counter
            return@Function it
        })
    }

    fun clearArticles() {
        articleList.clear()
        articleRepo.clearData()
    }

    fun saveArticles() {
        articleRepo.addArticle(articleList)
    }


    fun rateArticle(like: Boolean, position: Int) {
        counter += 1
        counterLiveData.value = counter
        articleList.removeAt(position).liked = like
        checkToggleConditions()
        //saveToReviewRepo
    }

    private fun checkToggleConditions() {
        if (articleList.isEmpty()) {
            toggleLiveData.value = true
        }
    }
}