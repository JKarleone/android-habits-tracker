package com.example.data

import androidx.room.TypeConverter
import com.example.data.Extensions.toHabitFrequency
import com.example.data.Extensions.toHabitPriority
import com.example.data.Extensions.toHabitType
import com.example.data.Extensions.toInt
import com.example.domain.utils.HabitFrequency
import com.example.domain.utils.HabitPriority
import com.example.domain.utils.HabitType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RoomConverter {

    @JvmStatic
    @TypeConverter
    fun toFrequency(value: Int): HabitFrequency = value.toHabitFrequency()

    @JvmStatic
    @TypeConverter
    fun fromFrequency(value: HabitFrequency): Int = value.toInt()

    @JvmStatic
    @TypeConverter
    fun toPriority(value: Int): HabitPriority = value.toHabitPriority()

    @JvmStatic
    @TypeConverter
    fun fromPriority(value: HabitPriority): Int = value.toInt()

    @JvmStatic
    @TypeConverter
    fun toHabitType(value: Int): HabitType = value.toHabitType()

    @JvmStatic
    @TypeConverter
    fun fromHabitType(value: HabitType): Int = value.toInt()

    @JvmStatic
    @TypeConverter
    fun fromDoneDates(value: List<Int>): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun toDoneDates(value: String): List<Int> {
        return Gson().fromJson(value, Array<Int>::class.java).toList()
    }

}