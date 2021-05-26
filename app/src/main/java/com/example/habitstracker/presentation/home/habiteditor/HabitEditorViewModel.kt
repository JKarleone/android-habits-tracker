package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import com.example.domain.Habit
import com.example.domain.HabitInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitEditorViewModel @Inject constructor(
    private val interactor: HabitInteractor
) : ViewModel() {

    var name: String? = null
    var description: String? = null
    var priority: com.example.domain.utils.HabitPriority? = null
    var type: com.example.domain.utils.HabitType? = null
    var frequencyTimes: Int? = null
    var frequency: com.example.domain.utils.HabitFrequency? = null
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
                    interactor.insertHabit(
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
                    interactor.updateHabit(
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