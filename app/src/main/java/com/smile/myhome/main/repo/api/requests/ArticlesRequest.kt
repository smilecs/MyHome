package com.smile.myhome.main.repo.api.requests

import com.past3.ketro.api.GenericRequestHandler
import com.smile.myhome.data.net.NetModule
import com.smile.myhome.main.repo.api.MainApiService
import com.smile.myhome.main.repo.api.response.ArticleResp
import retrofit2.Call

class ArticlesRequest(val limit: Int,
                      val locale: String,
                      val appDomain: Int) : GenericRequestHandler<ArticleResp>() {

    override fun makeRequest(): Call<ArticleResp> {
        return NetModule.provideRetrofit().
                create(MainApiService::class.java)
                .getSchedule(appDomain, locale, limit)
    }
}