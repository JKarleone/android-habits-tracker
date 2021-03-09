package com.example.habitstracker.utils

import androidx.core.content.ContextCompat
import com.example.habitstracker.App
import com.example.habitstracker.R

enum class HabitColor(val resourceId: Int) {

    DeepOrange(R.color.deep_orange_700),
    Orange(R.color.orange_700),
    Red(R.color.red_700),
    Pink(R.color.pink_700),
    Purple(R.color.purple_700),
    Indigo(R.color.indigo_700),
    Blue(R.color.blue_700),
    Green(R.color.green_700),

    DeepOrangeLight(R.color.deep_orange_200),
    OrangeLight(R.color.orange_200),
    RedLight(R.color.red_200),
    PinkLight(R.color.pink_200),
    PurpleLight(R.color.purple_200),
    IndigoLight(R.color.indigo_200),
    BlueLight(R.color.blue_200),
    GreenLight(R.color.green_200);

    fun getColor(): Int {
        return ContextCompat.getColor(App.applicationContext(), this.resourceId)
    }

    companion object {

        val colorToHabitColor = values()
                .map { ContextCompat.getColor(App.applicationContext(), it.resourceId) to it }.toMap()

        fun getHabitColor(color: Int): HabitColor? {
            for (elem in values())
                if (ContextCompat.getColor(App.applicationContext(), elem.resourceId) == color)
                    return elem

            return null
        }
    }

}