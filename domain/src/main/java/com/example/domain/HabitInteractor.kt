package com.example.domain

import com.example.domain.utils.HabitFrequency
import kotlinx.coroutines.flow.Flow
import java.util.*

class HabitInteractor (private val habitRepository: HabitRepository) {

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

    suspend fun habitDone(habit: Habit, date: Int) {
        habitRepository.habitDone(habit, date)
    }

    fun getGoalCount(habit: Habit): Int {
        return habit.frequencyTimes - calculateDoneHabits(habit)
    }

    private fun calculateDoneHabits(habit: Habit): Int {
        val currentCalendar = Calendar.getInstance()
        val frequencyField = when(habit.frequency) {
            HabitFrequency.EveryDay -> Calendar.DAY_OF_YEAR
            HabitFrequency.EveryWeek -> Calendar.WEEK_OF_YEAR
            HabitFrequency.EveryMonth -> Calendar.MONTH
            else -> Calendar.YEAR
        }

        var count = 0
        habit.doneDates
            .map { it.toLong() * 1000 }
            .forEach { date: Long ->
                val calendar = Calendar.getInstance()
                calendar.time = Date(date)

                if (calendar.get(frequencyField) == currentCalendar.get(frequencyField) &&
                    calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR))
                    count++
            }

        return count
    }

}