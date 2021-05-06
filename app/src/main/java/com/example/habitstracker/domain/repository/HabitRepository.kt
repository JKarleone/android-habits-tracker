package com.example.habitstracker.domain.repository

import androidx.lifecycle.LiveData
import com.example.habitstracker.data.AppDatabase
import com.example.habitstracker.data.entity.Habit

class HabitRepository {

    private val habitsDao = AppDatabase.getInstance().habitDao()

    fun getAllHabits(): LiveData<List<Habit>> =
            habitsDao.getAll()

}