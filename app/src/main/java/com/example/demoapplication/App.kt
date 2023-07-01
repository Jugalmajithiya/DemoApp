package com.example.demoapplication

import android.app.Application
lateinit var instanceApp: App

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instanceApp = this

    }
}