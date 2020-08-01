package com.example.githubbrowserwithflux

import android.app.Application
import com.facebook.stetho.Stetho

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}