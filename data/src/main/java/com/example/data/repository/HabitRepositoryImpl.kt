package com.example.data.repository

import android.util.Log
import com.example.data.Extensions.toHabit
import com.example.data.Extensions.toHabitData
import com.example.data.Extensions.toHabitModel
import com.example.data.dao.HabitDao
import com.example.data.network.api.HabitApi
import com.example.domain.Habit
import com.example.domain.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

class HabitRepositoryImpl(
    private val habitDao: HabitDao,
    private val habitApi: HabitApi
) : HabitRepository {

    override fun getAllHabits(): Flow<List<Habit>> =
            habitDao.getAll().transform { value -> emit(value.map { it.toHabit() }) }

    override suspend fun updateHabitsByServer() {
        val response = habitApi.getHabits()
        val serverHabitsList = response.body()?.map { it.toHabit().toHabitData() }
        Log.d(TAG, "body: ${response.body()}")
        Log.d(TAG, "error: ${response.errorBody()}")
        serverHabitsList?.let { habitDao.insert(serverHabitsList) }
    }

    override suspend fun insertHabit(habit: Habit) {
        val response = withContext(Dispatchers.IO) { habitApi.putHabit(habit.toHabitModel()) }
        if (response.isSuccessful) {
            val uid = response.body()?.uid
            uid?.let { habit.id = uid }
        }
        habitDao.insert(habit.toHabitData())
    }

    override suspend fun updateHabit(habit: Habit) {
        habit.date += 1
        habitDao.update(habit.toHabitData())
        habitApi.putHabit(habit.toHabitModel())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit.toHabitData())
        habit.id.let { habitApi.deleteHabit(com.example.data.network.model.HabitUID(habit.id)) }
    }

    companion object {
        private const val TAG = "repository"
    }

}