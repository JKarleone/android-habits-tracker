package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType
import java.io.Serializable

@Entity(tableName = "habits")
data class HabitData(
        var name: String,
        var description: String,
        var priority: HabitPriority,
        var type: HabitType,
        var frequencyTimes: Int,
        var frequency: HabitFrequency,
        var color: Int,
        var date: Int,
        var doneDates: List<Int>,
        @PrimaryKey
        var id: String
) : Serializable

