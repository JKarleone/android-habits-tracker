package com.example.habitstracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType
import java.io.Serializable

@Entity(tableName = "habits")
data class Habit(
        var name: String,
        var description: String,
        var priority: HabitPriority?,
        var type: HabitType?,
        var frequencyTimes: Int,
        var frequency: HabitFrequency?,
        var color: Int,
        @PrimaryKey(autoGenerate = true) var id: Long? = null
) : Serializable

