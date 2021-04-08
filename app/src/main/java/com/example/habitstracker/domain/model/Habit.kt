package com.example.habitstracker.domain.model

import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType
import java.io.Serializable

data class Habit(
        val name: String,
        val description: String,
        val priority: HabitPriority,
        val type: HabitType,
        val frequencyTimes: Int,
        val frequency: HabitFrequency,
        val color: Int,
        val id: Int = itemId++
) : Serializable {
        companion object {
                private var itemId = 0
        }
}
