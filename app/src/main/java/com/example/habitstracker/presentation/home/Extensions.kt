package com.example.habitstracker.presentation.home

import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.habitstracker.App
import com.example.habitstracker.R

object Extensions {

    fun HabitPriority.getString(): String {
        return App.applicationContext().getString(this.resourceId())
    }

    private fun HabitPriority.resourceId(): Int = when(this) {
        HabitPriority.Low -> R.string.habit_priority_low
        HabitPriority.Medium -> R.string.habit_priority_medium
        HabitPriority.High -> R.string.habit_priority_high
    }

    fun String.toHabitPriority(): HabitPriority {
        val context = App.applicationContext()
        return when(this) {
            context.getString(R.string.habit_priority_low) -> HabitPriority.Low
            context.getString(R.string.habit_priority_medium) -> HabitPriority.Medium
            else -> HabitPriority.High
        }
    }

    fun HabitFrequency.getString(): String {
        return App.applicationContext().getString(this.resourceId())
    }

    private fun HabitFrequency.resourceId(): Int = when(this) {
        HabitFrequency.EveryDay -> R.string.every_day
        HabitFrequency.EveryWeek -> R.string.every_week
        HabitFrequency.EveryMonth -> R.string.every_month
        HabitFrequency.EveryYear -> R.string.every_year
    }

    fun String.toHabitFrequency(): HabitFrequency {
        val context = App.applicationContext()
        return when(this) {
            context.getString(R.string.every_day) -> HabitFrequency.EveryDay
            context.getString(R.string.every_week) -> HabitFrequency.EveryWeek
            context.getString(R.string.every_month) -> HabitFrequency.EveryMonth
            else -> HabitFrequency.EveryYear
        }
    }

}