package com.example.habitstracker.di

import android.content.Context
import com.example.habitstracker.presentation.home.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class MapperModule {

    @Provides
    @Singleton
    fun provideMapper(context: Context): Mapper = Mapper(context)

}