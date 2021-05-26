package com.example.habitstracker.di

import com.example.data.dao.HabitDao
import com.example.data.network.api.HabitApi
import com.example.data.repository.HabitRepositoryImpl
import com.example.domain.HabitRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(habitDao: HabitDao, habitApi: HabitApi): HabitRepository {
        return HabitRepositoryImpl(habitDao, habitApi)
    }

}