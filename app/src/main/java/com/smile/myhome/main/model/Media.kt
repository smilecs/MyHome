package com.smile.myhome.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(val uri:String, val mimeType:String):Parcelable