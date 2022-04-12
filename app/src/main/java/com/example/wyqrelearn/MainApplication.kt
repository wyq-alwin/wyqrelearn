package com.example.wyqrelearn

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.TypedValue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}

