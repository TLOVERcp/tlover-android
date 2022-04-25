package com.cookandroid.teamproject1.util

import android.app.Application
import android.content.Context
import com.cookandroid.teamproject1.id.viewmodel.Prefs

class TloverApplication : Application(){
    companion object{
        var appContext : Context? = null

        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}