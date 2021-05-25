package com.example.habitstracker.data.dao

import androidx.room.*
import com.example.habitstracker.data.entity.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    fun getAll(): Flow<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habits: List<Habit>)

    @Delete
    suspend fun delete(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

}