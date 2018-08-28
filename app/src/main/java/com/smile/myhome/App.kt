package com.smile.myhome

import android.content.Context

class App : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private lateinit var appInstance: App
        fun getsInstance(): App = appInstance

        val appContext: Context
            get() = appInstance.applicationContext
    }
}