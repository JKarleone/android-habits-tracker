package com.example.habitstracker.data

import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.utils.HabitType

object Data {

    private val habits = ArrayList<Habit>()

    fun saveHabit(habit: Habit) {
        val index = indexOf(habit)

        if (index == -1)
            habits.add(habit)
        else
            habits[index] = habit
    }

    fun removeHabit(habit: Habit) {
        habits.remove(habit)
    }

    private fun indexOf(habit: Habit): Int {
        var index = -1
        for (i in habits.indices) {
            if (habits[i].id == habit.id)
                index = i
        }
        return index
    }

    fun getHabitsByType(habitType: HabitType): ArrayList<Habit> =
        ArrayList(habits.filter { it.type == habitType })

}