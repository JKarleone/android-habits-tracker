package com.example.habitstracker.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habitstracker.App
import com.example.habitstracker.data.dao.HabitDao
import com.example.habitstracker.data.entity.Habit

@Database(entities = [Habit::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        private const val NAME = "habitstracker_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance() : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.applicationContext(),
                    AppDatabase::class.java,
                    NAME
                ).allowMainThreadQueries().build()
                INSTANCE = instance

                return instance
            }
        }

    }

}