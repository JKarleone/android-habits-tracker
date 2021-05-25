package com.example.domain.utils

import java.io.Serializable

enum class HabitFrequency(val id: Int) : Serializable {
    EveryDay(0),
    EveryWeek(1),
    EveryMonth(2),
    EveryYear(3);
}