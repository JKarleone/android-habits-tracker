package com.example.habitstracker.di

import android.content.Context
import com.example.habitstracker.utils.ResourceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class ResourceManagerModule {

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager =
        ResourceManager(context)

}