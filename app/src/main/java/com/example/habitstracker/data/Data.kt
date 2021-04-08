package com.example.habitstracker.data

import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.utils.HabitType

object Data {

    private val habits = ArrayList<Habit>()

    fun addNewHabit(habit: Habit) {
        habits.add(habit)
    }

    fun removeHabit(habit: Habit) {
        habits.remove(habit)
    }

    fun updateHabit(newHabit: Habit) {
        for (i in habits.indices) {
            if (habits[i].id == newHabit.id)
                habits[i] = newHabit
        }
    }

    fun getHabitsByType(habitType: HabitType): ArrayList<Habit> =
        ArrayList(habits.filter { it.type == habitType })

}