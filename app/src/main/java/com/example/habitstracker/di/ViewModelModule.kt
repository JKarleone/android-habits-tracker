package com.example.habitstracker.di

import com.example.domain.HabitInteractor
import com.example.habitstracker.presentation.home.HabitsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [InteractorModule::class])
class ViewModelModule {

    @Provides
    @Singleton
    fun provideHabitsViewModel(interactor: HabitInteractor): HabitsViewModel {
        return HabitsViewModel(interactor)
    }

}