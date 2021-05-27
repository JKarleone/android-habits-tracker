package com.example.habitstracker.presentation.home

import android.content.Context
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.habitstracker.R

class Mapper(private val context: Context) {

    fun mapToString(priority: HabitPriority): String = priority.getString()
    fun mapToHabitPriority(string: String): HabitPriority = string.toHabitPriority()

    fun mapToString(frequency: HabitFrequency): String = frequency.getString()
    fun mapToHabitFrequency(string: String): HabitFrequency = string.toHabitFrequency()

    private fun HabitPriority.getString(): String {
        return context.getString(this.resourceId())
    }

    private fun HabitPriority.resourceId(): Int = when(this) {
        HabitPriority.Low -> R.string.habit_priority_low
        HabitPriority.Medium -> R.string.habit_priority_medium
        HabitPriority.High -> R.string.habit_priority_high
    }

    private fun String.toHabitPriority(): HabitPriority = when(this) {
        context.getString(R.string.habit_priority_low) -> HabitPriority.Low
        context.getString(R.string.habit_priority_medium) -> HabitPriority.Medium
        else -> HabitPriority.High
    }

    private fun HabitFrequency.getString(): String {
        return context.getString(this.resourceId())
    }

    private fun HabitFrequency.resourceId(): Int = when(this) {
        HabitFrequency.EveryDay -> R.string.every_day
        HabitFrequency.EveryWeek -> R.string.every_week
        HabitFrequency.EveryMonth -> R.string.every_month
        HabitFrequency.EveryYear -> R.string.every_year
    }

    private fun String.toHabitFrequency(): HabitFrequency = when(this) {
            context.getString(R.string.every_day) -> HabitFrequency.EveryDay
            context.getString(R.string.every_week) -> HabitFrequency.EveryWeek
            context.getString(R.string.every_month) -> HabitFrequency.EveryMonth
            else -> HabitFrequency.EveryYear
        }

}