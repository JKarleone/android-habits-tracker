package com.example.habitstracker.di

import android.content.Context
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.dao.HabitDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideHabitDao(database: AppDatabase): HabitDao {
        return database.habitDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            NAME
        ).build()
    }

    companion object {
        private const val NAME = "habitstracker_db"
    }

}