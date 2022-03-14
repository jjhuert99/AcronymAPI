package com.example.acronymapi.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AcronymAPI: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
