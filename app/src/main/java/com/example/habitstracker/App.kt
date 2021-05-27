package com.example.habitstracker

import android.app.Application
import com.example.habitstracker.di.ApplicationComponent
import com.example.habitstracker.di.ContextModule
import com.example.habitstracker.di.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}