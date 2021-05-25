package com.example.data.network.model

data class HabitModel(
    val color: Int,
    val count: Int,
    val date: Int,
    val description: String,
    val doneDates: List<Int>,
    val frequency: Int,
    val priority: Int,
    val title: String,
    val type: Int,
    val uid: String
)