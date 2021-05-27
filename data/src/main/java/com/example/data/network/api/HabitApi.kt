package com.example.data.network.api

import com.example.data.network.model.HabitDone
import com.example.data.network.model.HabitModel
import com.example.data.network.model.HabitUID
import retrofit2.Response
import retrofit2.http.*

interface HabitApi {

    @GET("habit")
    suspend fun getHabits(): Response<List<HabitModel>>

    @PUT("habit")
    suspend fun putHabit(@Body habit: HabitModel): Response<HabitUID>

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Body uid: HabitUID)

    @POST("habit_done")
    suspend fun habitDone(@Body habitDone: HabitDone)

}