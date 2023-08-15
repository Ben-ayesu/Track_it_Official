package com.example.trackitofficial

import android.app.Application
import com.example.trackitofficial.data.AppContainer
import com.example.trackitofficial.data.AppDataContainer

class TrackItApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}