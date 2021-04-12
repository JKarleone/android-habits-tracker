package com.example.habitstracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.domain.model.Habit

object Data {

    private val _habits = ArrayList<Habit>()
    private val mutableHabits = MutableLiveData(_habits)
    val habits: LiveData<ArrayList<Habit>> = mutableHabits

    fun saveHabit(habit: Habit) {
        val index = indexOf(habit)

        if (index == -1)
            _habits.add(habit)
        else
            _habits[index] = habit

        mutableHabits.value = _habits
    }

    fun removeHabit(habit: Habit) {
        _habits.remove(habit)

        mutableHabits.value = _habits
    }

    private fun indexOf(habit: Habit): Int {
        var index = -1
        for (i in _habits.indices) {
            if (_habits[i].id == habit.id)
                index = i
        }
        return index
    }

}