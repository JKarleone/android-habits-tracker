package com.example.habitstracker.utils

import com.example.habitstracker.App
import com.example.habitstracker.R
import java.io.Serializable

enum class HabitPriority(val resourceId: Int) : Serializable {
    High(R.string.habit_priority_high),
    Medium(R.string.habit_priority_medium),
    Low(R.string.habit_priority_low);

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