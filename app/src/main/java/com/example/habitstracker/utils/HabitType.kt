package com.example.habitstracker.utils

import com.example.habitstracker.App
import com.example.habitstracker.R

enum class HabitType(val resourceId: Int) {
    Bad(R.string.habit_type_bad),
    Good(R.string.habit_type_good);

    override fun toString(): String {
        return App.applicationContext().getString(this.resourceId)
    }

    companion object {

        fun getHabitTypeByString(resourceStr: String): HabitType? {
            for (elem in values())
                if (elem.toString() == resourceStr)
                    return elem

            return null
        }

        fun getHabitTypeByResourceId(resourceId: Int): HabitType? {
            for (elem in values())
                if (elem.resourceId == resourceId)
                    return elem

            return null
        }

    }

}
