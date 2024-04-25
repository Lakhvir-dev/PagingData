package com.app.myapplication.apiResponseHandler.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun get(): MyApplication = instance
    }
}
