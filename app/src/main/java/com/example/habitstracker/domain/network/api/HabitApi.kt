package com.example.habitstracker.domain.network.api

import com.example.habitstracker.domain.network.model.HabitModel
import com.example.habitstracker.domain.network.model.HabitUID
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface HabitApi {

    @GET("habit")
    suspend fun getHabits(): Response<List<HabitModel>>

    @PUT("habit")
    suspend fun putHabit(@Body habit: HabitModel): Response<HabitUID>

    @DELETE("habit")
    suspend fun deleteHabit(@Body uid: String)

}