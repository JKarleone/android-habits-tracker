package com.example.domain.utils

import java.io.Serializable

enum class HabitPriority(val id: Int) : Serializable {
    Low(0),
    Medium(1),
    High(2);
}