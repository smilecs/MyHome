package com.smile.myhome.main.repo.api.response

import com.squareup.moshi.Json

data class ArticleResp(@Json(name = "_embedded") val data: EmbeddedResp)