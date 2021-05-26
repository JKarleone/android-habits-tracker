package com.example.habitstracker.presentation.home

import androidx.lifecycle.*
import com.example.domain.Habit
import com.example.domain.HabitInteractor
import com.example.domain.utils.HabitType
import com.example.habitstracker.utils.SortField
import com.example.habitstracker.utils.SortUtil.getSortedList
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitsViewModel @Inject constructor(
    private val habitInteractor: HabitInteractor
) : ViewModel() {

    var searchSubstring: MutableLiveData<String> = MutableLiveData("")

    var sortField: MutableLiveData<SortField> = MutableLiveData(SortField.NAME)

    var sortByAscending: MutableLiveData<Boolean> = MutableLiveData(true)

    private val habits = habitInteractor.getAllHabits().asLiveData()

    val goodHabits: MediatorLiveData<List<Habit>> = MediatorLiveData()
    val badHabits: MediatorLiveData<List<Habit>> = MediatorLiveData()

    init {
        val observer = Observer<Any> {
            updateHabitsData()
        }
        goodHabits.addSource(habits, observer)
        goodHabits.addSource(searchSubstring, observer)
        goodHabits.addSource(sortField, observer)
        goodHabits.addSource(sortByAscending, observer)
        badHabits.addSource(habits, observer)
        badHabits.addSource(searchSubstring, observer)
        badHabits.addSource(sortField, observer)
        badHabits.addSource(sortByAscending, observer)

        viewModelScope.launch {
            habitInteractor.updateHabitsByServer()
        }
    }

    private fun updateHabitsData() {
        habits.value?.let {
            goodHabits.value = getHabitsByType(it, HabitType.Good)
            badHabits.value = getHabitsByType(it, HabitType.Bad)
        }
    }

    private fun getHabitsByType(habits: List<Habit>, habitType: HabitType): MutableList<Habit> {
        val filteredHabits = habits.filter {
            it.type == habitType &&
            (it.name.contains(searchSubstring.value!!, true) || it.description.contains(searchSubstring.value!!, true))
        }.toMutableList()
        return filteredHabits.getSortedList(sortField.value!!, sortByAscending.value!!)
    }

}