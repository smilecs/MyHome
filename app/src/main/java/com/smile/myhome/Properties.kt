package com.smile.myhome

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.Delegates

object Properties{
    private const val USER_REVIEW_LIMIT = "limit"
    private const val DEFAULT_LIMIT = 10
    var props: SharedPreferences by Delegates.notNull()

    init {
        props = App.getsInstance().getSharedPreferences("home_pref", Context.MODE_PRIVATE)
    }

    fun setLimit(limit:Int) = props.edit().putInt(USER_REVIEW_LIMIT, limit).apply()

    fun getLimit() = props.getInt(USER_REVIEW_LIMIT, DEFAULT_LIMIT)
}