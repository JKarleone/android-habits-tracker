package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.AppDatabase
import com.example.habitstracker.data.entity.Habit
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

class HabitEditorViewModel : ViewModel() {

    private val habitDao by lazy { AppDatabase.getInstance().habitDao() }

    var name: String? = null
    var description: String? = null
    var priority: HabitPriority? = null
    var type: HabitType? = null
    var frequencyTimes: Int? = null
    var frequency: HabitFrequency? = null
    var color: Int? = null
    var id: Long? = null

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
                habitDao.insert(
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
                habitDao.update(
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