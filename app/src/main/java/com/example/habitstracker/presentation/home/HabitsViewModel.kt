package com.example.habitstracker.presentation.home

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.domain.Habit
import com.example.domain.HabitInteractor
import com.example.domain.utils.HabitType
import com.example.habitstracker.utils.HabitToastHelper
import com.example.habitstracker.utils.SortField
import com.example.habitstracker.utils.SortUtil.getSortedList
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HabitsViewModel @Inject constructor(
    private val habitInteractor: HabitInteractor,
    private val toastHelper: HabitToastHelper
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

    fun completeHabit(habit: Habit): String {
        val date = (Date().time / 1000).toInt()
        val goalCount = habitInteractor.getGoalCount(habit) - 1

        habitDone(habit, date)

        return toastHelper.getToastText(goalCount, habit.type)
    }

    private fun habitDone(habit: Habit, date: Int) {
        viewModelScope.launch {
            habitInteractor.habitDone(habit, date)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            habitInteractor.deleteHabit(habit)
        }
    }

}