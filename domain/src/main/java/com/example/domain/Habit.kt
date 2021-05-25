package com.example.domain

import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType
import java.io.Serializable

data class Habit(
    var name: String,
    var description: String,
    var priority: HabitPriority,
    var type: HabitType,
    var frequencyTimes: Int,
    var frequency: HabitFrequency,
    var color: Int,
    var date: Int,
    var id: String
) : Serializable