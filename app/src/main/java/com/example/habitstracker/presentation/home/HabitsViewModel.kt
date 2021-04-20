package com.example.habitstracker.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.data.AppDatabase
import com.example.habitstracker.data.entity.Habit
import com.example.habitstracker.utils.HabitType
import com.example.habitstracker.utils.SortField
import com.example.habitstracker.utils.SortUtil.getSortedList

class HabitsViewModel : ViewModel() {

    private val habitDao = AppDatabase.getInstance().habitDao()

    val habits = habitDao.getAll()

    var searchSubstring: MutableLiveData<String> = MutableLiveData("")

    var sortField: MutableLiveData<SortField> = MutableLiveData(SortField.NAME)

    var sortByAscending: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getHabitsByType(habitType: HabitType): ArrayList<Habit> {
        val filteredHabits = ArrayList(habits.value?.filter {
            it.type == habitType && (it.name.contains(searchSubstring.value!!, true) || it.description.contains(searchSubstring.value!!, true))
        } ?: emptyList() )
        return filteredHabits.getSortedList(sortField.value!!, sortByAscending.value!!)
    }

}