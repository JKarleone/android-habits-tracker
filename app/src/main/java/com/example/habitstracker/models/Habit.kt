package com.example.habitstracker.models

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
        val color: Int
) : Serializable
