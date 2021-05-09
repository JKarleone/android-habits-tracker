package com.example.habitstracker.domain.repository

import androidx.lifecycle.LiveData
import com.example.habitstracker.data.AppDatabase
import com.example.habitstracker.data.entity.Habit

class HabitRepository {

    private val habitsDao = AppDatabase.getInstance().habitDao()

    fun getAllHabits(): LiveData<List<Habit>> =
            habitsDao.getAll()

    suspend fun insertHabit(habit: Habit) {
        habitsDao.insert(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitsDao.update(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitsDao.delete(habit)
    }

}