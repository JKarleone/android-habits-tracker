package com.example.habitstracker.presentation.home

import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.Data
import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.utils.HabitType

class HabitsViewModel : ViewModel() {

    val habits = Data.habits

    fun getHabitsByType(habitType: HabitType): ArrayList<Habit> {
        return ArrayList(habits.value?.filter { it.type == habitType } )
    }

}