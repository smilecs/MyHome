package com.smile.myhome.main.api

import com.smile.myhome.net.Urls
import com.smile.myhome.main.api.response.ArticleResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {

    @GET(Urls.GET_ARTICLES)
    fun getSchedule(@Query("appDomain") appDomain: String,
                    @Query("locale")
                    locale: String,
                    @Query("limit") limit: Int): Call<ArticleResp>

}