package com.example.habitstracker.domain.repository

import android.util.Log
import com.example.habitstracker.data.AppDatabase
import com.example.habitstracker.data.entity.Habit
import com.example.habitstracker.domain.network.Extensions.toHabit
import com.example.habitstracker.domain.network.Extensions.toHabitModel
import com.example.habitstracker.domain.network.RetrofitInstance
import com.example.habitstracker.domain.network.model.HabitUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HabitRepository {

    private val habitsDao = AppDatabase.getInstance().habitDao()
    private val habitApi = RetrofitInstance.api

    fun getAllHabits(): Flow<List<Habit>> =
            habitsDao.getAll()

    suspend fun updateHabitsByServer() {
        val response = habitApi.getHabits()
        val serverHabitsList = response.body()?.map { it.toHabit() }
        Log.d(TAG, "body: ${response.body()}")
        Log.d(TAG, "error: ${response.errorBody()}")
        serverHabitsList?.let { habitsDao.insert(serverHabitsList) }
    }

    suspend fun insertHabit(habit: Habit) {
        val response = withContext(Dispatchers.IO) { habitApi.putHabit(habit.toHabitModel()) }
        if (response.isSuccessful) {
            val uid = response.body()?.uid
            uid?.let { habit.id = uid }
        }
        habitsDao.insert(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habit.date += 1
        habitsDao.update(habit)
        habitApi.putHabit(habit.toHabitModel())
    }

    suspend fun deleteHabit(habit: Habit) {
        habitsDao.delete(habit)
        habit.id.let { habitApi.deleteHabit(HabitUID(habit.id)) }
    }

    companion object {
        private const val TAG = "repository"
    }

}