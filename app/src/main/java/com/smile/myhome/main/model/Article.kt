package com.smile.myhome.main.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(var description: String? = "",
                   @PrimaryKey(autoGenerate = false)
                   var sku: String = "",
                   var title: String = "",
                   @Ignore
                   val media: List<Media> = listOf(),
                   var liked: Boolean = false,
                   var reviewed: Boolean = false,
                   var imageString: String? = "") : Parcelable