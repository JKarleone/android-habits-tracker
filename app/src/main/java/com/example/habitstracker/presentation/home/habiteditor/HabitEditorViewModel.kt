package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.entity.Habit
import com.example.habitstracker.domain.repository.HabitRepository
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HabitEditorViewModel : ViewModel() {

    private val habitRepository = HabitRepository()

    var name: String? = null
    var description: String? = null
    var priority: HabitPriority? = null
    var type: HabitType? = null
    var frequencyTimes: Int? = null
    var frequency: HabitFrequency? = null
    var color: Int? = null
    var date: Int? = null
    var id: String? = null

    fun setData(habit: Habit?) {
        name = habit?.name
        description = habit?.description
        priority = habit?.priority
        type = habit?.type
        frequencyTimes = habit?.frequencyTimes
        frequency = habit?.frequency
        color = habit?.color
        date = habit?.date
        id = habit?.id
    }

    fun saveHabit() {
        if (isCorrectToSave()) {
            GlobalScope.launch {
                if (id == null)
                    habitRepository.insertHabit(
                            Habit(
                                    name!!,
                                    description!!,
                                    priority!!,
                                    type!!,
                                    frequencyTimes!!,
                                    frequency!!,
                                    color!!,
                                    0,
                                ""
                            )
                    )
                else
                    habitRepository.updateHabit(
                            Habit(
                                    name!!,
                                    description!!,
                                    priority!!,
                                    type!!,
                                    frequencyTimes!!,
                                    frequency!!,
                                    color!!,
                                    date!!,
                                    id!!
                            )
                    )
            }
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