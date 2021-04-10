package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.Data
import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

class HabitEditorViewModel : ViewModel() {

    var name: String? = null
    var description: String? = null
    var priority: HabitPriority? = null
    var type: HabitType? = null
    var frequencyTimes: Int? = null
    var frequency: HabitFrequency? = null
    var color: Int? = null
    var id: Int? = null

    fun setData(habit: Habit?) {
        name = habit?.name
        description = habit?.description
        priority = habit?.priority
        type = habit?.type
        frequencyTimes = habit?.frequencyTimes
        frequency = habit?.frequency
        color = habit?.color
        id = habit?.id
    }

    fun saveHabit() {
        if (isCorrectToSave()) {
            if (id == null)
                Data.saveHabit(
                    Habit(
                        name!!,
                        description!!,
                        priority!!,
                        type!!,
                        frequencyTimes!!,
                        frequency!!,
                        color!!
                    )
                )
            else
                Data.saveHabit(
                    Habit(
                        name!!,
                        description!!,
                        priority!!,
                        type!!,
                        frequencyTimes!!,
                        frequency!!,
                        color!!,
                        id!!
                    )
                )
        }
    }

    private fun isCorrectToSave(): Boolean {
        return  name != null &&
                description != null &&
                priority != null &&
                type != null &&
                frequencyTimes != null &&
                frequency != null &&
                color != null
    }

}