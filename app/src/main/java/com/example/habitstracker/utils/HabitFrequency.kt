package com.example.habitstracker.utils

import com.example.habitstracker.App
import com.example.habitstracker.R
import java.io.Serializable

enum class HabitFrequency(val resourceId: Int) : Serializable {
    EveryDay(R.string.every_day),
    EveryWeek(R.string.every_week),
    EveryMonth(R.string.every_month),
    EveryYear(R.string.every_year);

    override fun toString(): String {
        return App.applicationContext().getString(this.resourceId)
    }

    companion object {

        fun getHabitFrequencyByString(resourceStr: String): HabitFrequency? {
            for (elem in values())
                if (elem.toString() == resourceStr)
                    return elem

            return null
        }

        fun getHabitFrequencyByResourceId(resourceId: Int): HabitFrequency? {
            for (elem in values())
                if (elem.resourceId == resourceId)
                    return elem

            return null
        }

    }

}