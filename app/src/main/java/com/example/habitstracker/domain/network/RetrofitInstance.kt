package com.example.habitstracker.domain.network

import android.util.Log
import com.example.habitstracker.domain.network.api.HabitApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://droid-test-server.doubletapp.ru/api/"
    private const val TOKEN = "821ba30d-f0b5-4470-86d9-ab00c74dcdf4"

    private const val DELAY_MS = 5000L
    private const val TAG = "Retrofit"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val interceptor by lazy {
        Interceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", TOKEN)
                .build()
            var response = it.proceed(request)
            val code = response.code()

            if (code != 200) {
                do {
                    response.close()
                    Thread.sleep(DELAY_MS)
                    Log.d(TAG, "Try to get response, previous code: $code")
                    response = it.proceed(request)
                } while (response.code() == code)
            }

            return@Interceptor response
        }
    }

    val api: HabitApi by lazy {
        retrofit.create(HabitApi::class.java)
    }

}
