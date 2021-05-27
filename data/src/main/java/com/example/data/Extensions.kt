package com.example.data

import com.example.data.entity.HabitData
import com.example.data.network.model.HabitModel
import com.example.domain.Habit
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType

object Extensions {

    fun Habit.toHabitModel(): HabitModel {
        return HabitModel(
            this.color,
            this.frequencyTimes,
            this.date,
            this.description,
            this.doneDates,
            this.frequency.toInt(),
            this.priority.toInt(),
            this.name,
            this.type.toInt(),
            this.id
        )
    }

    fun HabitModel.toHabit(): Habit {
        return Habit(
            this.title,
            this.description,
            this.priority.toHabitPriority(),
            this.type.toHabitType(),
            this.count,
            this.frequency.toHabitFrequency(),
            this.color,
            this.date,
            this.done_dates,
            this.uid
        )
    }

    fun Habit.toHabitData(): HabitData {
        return HabitData(
            this.name,
            this.description,
            this.priority,
            this.type,
            this.frequencyTimes,
            this.frequency,
            this.color,
            this.date,
            this.doneDates,
            this.id
        )
    }

    fun HabitData.toHabit(): Habit {
        return Habit(
            this.name,
            this.description,
            this.priority,
            this.type,
            this.frequencyTimes,
            this.frequency,
            this.color,
            this.date,
            this.doneDates,
            this.id
        )
    }

    fun HabitPriority.toInt(): Int = when(this) {
        HabitPriority.Low -> 0
        HabitPriority.Medium -> 1
        HabitPriority.High -> 2
    }

    fun Int.toHabitPriority(): HabitPriority = when(this) {
        0 -> HabitPriority.Low
        1 -> HabitPriority.Medium
        else -> HabitPriority.High
    }

    fun HabitType.toInt(): Int = when(this) {
        HabitType.Good -> 0
        HabitType.Bad -> 1
    }

    fun Int.toHabitType(): HabitType = when(this) {
        0 -> HabitType.Good
        else -> HabitType.Bad
    }

    fun HabitFrequency.toInt(): Int = when(this) {
        HabitFrequency.EveryDay -> 0
        HabitFrequency.EveryWeek -> 1
        HabitFrequency.EveryMonth -> 2
        HabitFrequency.EveryYear -> 3
    }

    fun Int.toHabitFrequency(): HabitFrequency = when(this) {
        0 -> HabitFrequency.EveryDay
        1 -> HabitFrequency.EveryWeek
        2 -> HabitFrequency.EveryMonth
        else -> HabitFrequency.EveryYear
    }

}