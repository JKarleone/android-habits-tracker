package com.example.habitstracker.di

import com.example.data.network.api.HabitApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideHabitApi(retrofit: Retrofit): HabitApi {
        return retrofit.create(HabitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideInterceptor(): Interceptor =
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
                    response = it.proceed(request)
                } while (response.code() == code)
            }

            return@Interceptor response
        }

    companion object {

        private const val BASE_URL = "https://droid-test-server.doubletapp.ru/api/"
        private const val TOKEN = "821ba30d-f0b5-4470-86d9-ab00c74dcdf4"
        private const val DELAY_MS = 5000L

    }

}