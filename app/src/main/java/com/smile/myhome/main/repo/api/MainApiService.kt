package com.smile.myhome.main.repo.api

import com.smile.myhome.data.net.Urls
import com.smile.myhome.main.repo.api.response.ArticleResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {

    @GET(Urls.GET_ARTICLES)
    fun getSchedule(@Query("appDomain") appDomain: Int,
                    @Query("locale")
                    locale: String,
                    @Query("limit") limit: Int): Call<ArticleResp>

}