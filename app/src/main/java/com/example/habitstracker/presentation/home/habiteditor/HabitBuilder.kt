package com.example.habitstracker.presentation.home.habiteditor

import com.example.domain.Habit
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType

class HabitBuilder(var habit: Habit? = null) {

    var name: String? = null
    var description: String? = null
    var priority: HabitPriority? = null
    var type: HabitType? = null
    var frequencyTimes: Int? = null
    var frequency: HabitFrequency? = null
    var color: Int? = null
    var date: Int? = null
    var doneDates: List<Int>? = null
    var id: String? = null

    init {
        name = habit?.name
        description = habit?.description
        priority = habit?.priority
        type = habit?.type
        frequencyTimes = habit?.frequencyTimes
        frequency = habit?.frequency
        color = habit?.color
        date = habit?.date
        doneDates = habit?.doneDates
        id = habit?.id
    }

    fun build(): Habit =
        if (id == null) {
            Habit(
                name!!,
                description!!,
                priority!!,
                type!!,
                frequencyTimes!!,
                frequency!!,
                color!!,
                0,
                emptyList(),
                ""
            )
        }
        else {
            Habit(
                name!!,
                description!!,
                priority!!,
                type!!,
                frequencyTimes!!,
                frequency!!,
                color!!,
                date!!,
                doneDates!!,
                id!!
            )
        }


    fun isCorrectToBuild(): Boolean {
        return  name != null &&
                description != null &&
                priority != null &&
                type != null &&
                frequencyTimes != null &&
                frequency != null &&
                color != null
    }
}