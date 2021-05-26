package com.example.habitstracker.di

import com.example.domain.HabitInteractor
import com.example.domain.HabitRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideHabitInteractor(habitRepository: HabitRepository): HabitInteractor {
        return HabitInteractor(habitRepository)
    }

}