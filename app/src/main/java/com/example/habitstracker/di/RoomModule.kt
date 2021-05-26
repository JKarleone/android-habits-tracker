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
        val name = "habitstracker_db"
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            name
        ).build()
    }

}