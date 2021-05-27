package com.example.habitstracker.utils

import android.content.Context
import com.example.domain.utils.HabitType
import com.example.habitstracker.R

class HabitToastHelper(private val context: Context) {

    fun getToastText(goalCount: Int, type: HabitType): String {
        return when {
            goalCount > 0 -> lessThanGoal(type, goalCount)
            goalCount == 0 -> context.getString(R.string.habit_goal_is_achived)
            goalCount < 0 -> moreThanGoal(type)
            else -> "Error"
        }
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