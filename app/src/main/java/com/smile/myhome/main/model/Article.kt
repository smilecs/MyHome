package com.smile.myhome.main.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(@PrimaryKey(autoGenerate = true) var id: Int?,
                   val description: String?,
                   val sku: String,
                   val title: String,
                   val media: List<Media>) : Parcelable