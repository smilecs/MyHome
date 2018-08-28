package com.smile.myhome.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val description: String?,
                   val sku: String,
                   val title: String,
                   val media: List<Media>) : Parcelable