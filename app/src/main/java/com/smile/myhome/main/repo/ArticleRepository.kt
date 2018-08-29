package com.smile.myhome.main.repo

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.past3.ketro.model.Wrapper
import com.smile.myhome.App
import com.smile.myhome.Properties
import com.smile.myhome.R
import com.smile.myhome.data.database.AppDataBase
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.repo.api.requests.ArticlesRequest
import com.smile.myhome.main.repo.dao.ArticleDao
import org.jetbrains.anko.doAsync

class ArticleRepository : ArticleDataSource {
    companion object {
        const val LOCAL_SOURCE = 1
        const val REMOTE_SOURCE = 2
    }

    private val articleDao: ArticleDao by lazy {
        AppDataBase.getInstance(App.appContext).articleDao()
    }

    override fun getArticles(source: Int): LiveData<Wrapper<List<Article>>> {
        return when (source) {
            LOCAL_SOURCE -> getLocalArticles()
            REMOTE_SOURCE -> getRemoteArticles()
            else -> throw IllegalArgumentException()
        }
    }

    override fun addArticle(article: List<Article>) {
        doAsync {
            article.forEach {
                articleDao.addArticle(it)
            }
        }
    }

    override fun clearData() {
        doAsync {
            articleDao.nukeTable()
        }
    }

    //for now only reviewed articles are persisted
    private fun getLocalArticles(): LiveData<Wrapper<List<Article>>> {
        return Transformations.map(articleDao.getArticles(), Function {
            val wrapper = Wrapper<List<Article>>()
            wrapper.data = it
            return@Function wrapper
        })
    }

    private fun getRemoteArticles(): LiveData<Wrapper<List<Article>>> {
        val res = App.getsInstance().resources
        val articleReq = ArticlesRequest(res.getInteger(R.integer.limit),
                res.getString(R.string.locale),
                res.getInteger(R.integer.appDomain))
        return Transformations.map(articleReq.doRequest(), Function { resp ->
            return@Function Wrapper<List<Article>>().apply {
                exception = resp.exception
                data = resp.data?.data?.articles
            }
        })
    }
}