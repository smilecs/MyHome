package com.smile.myhome.main.api.response

import com.squareup.moshi.Json

data class ArticleResp(@Json(name = "_embedded") val data: EmbeddedResp)