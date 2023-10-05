package com.example.moviesapp.app

import android.app.Application

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoviesApp
            private set
    }
}