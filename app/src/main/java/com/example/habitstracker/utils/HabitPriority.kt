package com.example.habitstracker.utils

import com.example.habitstracker.App
import com.example.habitstracker.R
import java.io.Serializable

enum class HabitPriority(private val resourceId: Int) : Serializable {
    Low(R.string.habit_priority_low),
    Medium(R.string.habit_priority_medium),
    High(R.string.habit_priority_high);

    override fun toString(): String {
        return App.applicationContext().getString(this.resourceId)
    }

    companion object {

        fun getHabitPriorityByString(resourceStr: String): HabitPriority? {
            for (elem in values())
                if (elem.toString() == resourceStr)
                    return elem

            return null
        }

    }

}