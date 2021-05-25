package com.example.habitstracker.domain.network

import com.example.habitstracker.data.entity.Habit
import com.example.habitstracker.domain.network.model.HabitModel
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

object Extensions {

    fun Habit.toHabitModel(): HabitModel {
        return HabitModel(
            this.color,
            this.frequencyTimes,
            this.date,
            this.description,
            emptyList(),
            frequency!!.resourceId,
            this.priority!!.toInt(),
            this.name,
            this.type!!.toInt(),
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
            HabitFrequency.getHabitFrequencyByResourceId(this.frequency),
            this.color,
            this.date,
            this.uid
        )
    }

    private fun HabitPriority.toInt(): Int = when(this) {
        HabitPriority.Low -> 0
        HabitPriority.Medium -> 1
        else -> 2
    }

    private fun Int.toHabitPriority(): HabitPriority = when(this) {
        0 -> HabitPriority.Low
        1 -> HabitPriority.Medium
        else -> HabitPriority.High
    }

    private fun HabitType.toInt(): Int = when(this) {
        HabitType.Good -> 0
        else -> 1
    }

    private fun Int.toHabitType(): HabitType = when(this) {
        0 -> HabitType.Good
        else -> HabitType.Bad
    }

}