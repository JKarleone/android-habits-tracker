package com.example.habitstracker.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.Data
import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.utils.HabitType
import com.example.habitstracker.utils.SortField
import com.example.habitstracker.utils.SortUtil.getSortedListByAscending

class HabitsViewModel : ViewModel() {

    val habits = Data.habits

    var searchSubstring: MutableLiveData<String> = MutableLiveData("")

    var sortField: MutableLiveData<SortField> = MutableLiveData(SortField.NAME)

    fun getHabitsByType(habitType: HabitType): ArrayList<Habit> {
        val filteredHabits = ArrayList(habits.value?.filter {
            it.type == habitType && (it.name.contains(searchSubstring.value!!, true) || it.description.contains(searchSubstring.value!!, true))
        } )
        return filteredHabits.getSortedListByAscending(sortField.value!!)
    }

}