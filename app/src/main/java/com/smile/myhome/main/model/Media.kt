package com.smile.myhome.main.model

import android.arch.persistence.room.TypeConverter
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Media(val uri: String, val mimeType: String) : Parcelable {
    companion object {
        class MediaRoomTypeConverter {
            private val gson = Gson()

            @TypeConverter
            fun stringToMediaObjectList(data: String?): List<Media> {
                if (data == null) {
                    return Collections.emptyList()
                }

                val listType = object : TypeToken<List<Media>>() {

                }.type

                return gson.fromJson(data, listType)
            }

            @TypeConverter
            fun mediaObjectListToString(someObjects: List<Media>): String {
                return gson.toJson(someObjects)
            }
        }
    }
}