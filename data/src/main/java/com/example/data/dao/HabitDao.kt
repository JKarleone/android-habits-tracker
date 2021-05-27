package com.example.data.dao

import androidx.room.*
import com.example.data.entity.HabitData
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    fun getAll(): Flow<List<HabitData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitData: HabitData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitData: List<HabitData>)

    @Delete
    suspend fun delete(habitData: HabitData)

    @Update
    suspend fun update(habitData: HabitData)

}