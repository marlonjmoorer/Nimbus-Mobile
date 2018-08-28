package com.marlonmoorer.nimbus

import android.app.Application
import android.content.res.Resources


class App:Application() {



    companion object {
       lateinit var Resources:Resources
    }



    override fun onCreate() {
        super.onCreate()
        Resources=this.resources

    }
}