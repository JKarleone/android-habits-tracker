package com.example.habitstracker.utils

import android.content.Context
import android.content.res.Resources
import com.example.domain.Habit
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitType
import com.example.habitstracker.R
import java.util.*

class HabitToastHelper(private val context: Context) {

    fun getToastText(habit: Habit): String {
        val doneTimes = calculateDoneHabits(habit) + 1
        val result = habit.frequencyTimes - doneTimes

        return when {
            result > 0 -> lessThanGoal(habit.type, result)
            result == 0 -> context.getString(R.string.habit_goal_is_achived)
            result < 0 -> moreThanGoal(habit.type)
            else -> "Error"
        }
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

    private fun lessThanGoal(habitType: HabitType, count: Int): String {
        return when(habitType){
            HabitType.Bad -> context.resources.getQuantityString(R.plurals.habit_bad_toast_less_than_goal, count, count)
            HabitType.Good -> context.resources.getQuantityString(R.plurals.habit_good_toast_less_than_goal, count, count)
        }
    }

    private fun moreThanGoal(habitType: HabitType): String {
        return when(habitType) {
            HabitType.Bad -> context.getString(R.string.habit_bad_toast_more_than_goal)
            HabitType.Good -> context.getString(R.string.habit_good_toast_more_than_goal)
        }
    }

}