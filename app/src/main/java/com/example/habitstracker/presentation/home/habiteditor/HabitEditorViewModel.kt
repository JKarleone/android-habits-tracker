package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import com.example.domain.Habit
import com.example.domain.HabitInteractor
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitEditorViewModel @Inject constructor(
    private val interactor: HabitInteractor
) : ViewModel() {

    var habitBuilder: HabitBuilder = HabitBuilder()

    fun setData(habit: Habit?) {
        habitBuilder = HabitBuilder(habit)
    }

    fun saveHabit() {
        if (isCorrectToSave())
            GlobalScope.launch {
                val habit = habitBuilder.build()
                if (habitBuilder.id == null)
                    interactor.insertHabit(habit)
                else
                    interactor.updateHabit(habit)
            }
    }

    private fun isCorrectToSave(): Boolean {
        return habitBuilder.isCorrectToBuild()
    }

}