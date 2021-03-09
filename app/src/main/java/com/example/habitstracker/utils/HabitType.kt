package com.example.habitstracker.utils

import com.example.habitstracker.App
import com.example.habitstracker.R

enum class HabitType(val resourceId: Int) {
    Bad(R.string.habit_type_bad),
    Good(R.string.habit_type_good);

    override fun toString(): String {
        return App.applicationContext().getString(this.resourceId)
    }

}
