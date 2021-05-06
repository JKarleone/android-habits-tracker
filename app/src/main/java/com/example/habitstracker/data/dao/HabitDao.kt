package com.example.habitstracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habitstracker.data.entity.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    fun getAll(): LiveData<List<Habit>>

    @Insert
    suspend fun insert(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

}