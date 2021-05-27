package com.example.habitstracker.di

import android.content.Context
import com.example.habitstracker.utils.HabitToastHelper
import dagger.Module
import dagger.Provides

@Module
class ToastHelperModule {

    @Provides
    @ActivityScope
    fun provideToastHelper(context: Context): HabitToastHelper =
        HabitToastHelper(context)

}