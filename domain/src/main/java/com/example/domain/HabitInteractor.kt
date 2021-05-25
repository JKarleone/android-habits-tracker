package com.example.domain

import kotlinx.coroutines.flow.Flow

class HabitInteractor(private val habitRepository: HabitRepository) {

    fun getAllHabits(): Flow<List<Habit>> = habitRepository.getAllHabits()

    suspend fun updateHabitsByServer() {
        habitRepository.updateHabitsByServer()
    }

    suspend fun insertHabit(habit: Habit) {
        habitRepository.insertHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitRepository.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitRepository.deleteHabit(habit)
    }

}