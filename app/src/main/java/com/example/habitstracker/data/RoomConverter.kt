package com.example.habitstracker.data

import androidx.room.TypeConverter
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

object RoomConverter {

    @JvmStatic
    @TypeConverter
    fun toFrequency(value: Int): HabitFrequency? =
        HabitFrequency.getHabitFrequencyByResourceId(value)

    @JvmStatic
    @TypeConverter
    fun fromFrequency(value: HabitFrequency): Int = value.resourceId

    @JvmStatic
    @TypeConverter
    fun toPriority(value: Int): HabitPriority? =
        HabitPriority.getHabitPriorityByResourceId(value)

    @JvmStatic
    @TypeConverter
    fun fromPriority(value: HabitPriority): Int = value.resourceId

    @JvmStatic
    @TypeConverter
    fun toHabitType(value: Int): HabitType? =
        HabitType.getHabitTypeByResourceId(value)

    @JvmStatic
    @TypeConverter
    fun fromHabitType(value: HabitType): Int = value.resourceId

}