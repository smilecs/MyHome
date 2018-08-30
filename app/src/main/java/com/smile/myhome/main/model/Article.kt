package com.smile.myhome.main.model

import android.arch.persistence.room.*
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(var description: String? = "",
                   @PrimaryKey(autoGenerate = false)
                   var sku: String = "",
                   var title: String = "",
                   @TypeConverters(Media.Companion.MediaRoomTypeConverter::class)
                   var media: List<Media> = mutableListOf(),
                   var liked: Boolean = false,
                   var reviewed: Boolean = false) : Parcelable