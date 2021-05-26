package com.example.habitstracker

import android.app.Application
import com.example.habitstracker.di.ApplicationComponent
import com.example.habitstracker.di.ContextModule
import com.example.habitstracker.di.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

//    init {
//        instance = this
//    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

//    companion object {
//        private var instance: App? = null
//
//        fun applicationContext(): Context {
//            return instance!!.applicationContext
//        }
//    }

}