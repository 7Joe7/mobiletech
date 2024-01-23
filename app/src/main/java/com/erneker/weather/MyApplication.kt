package com.erneker.weather

import android.app.Application
import com.erneker.weather.api.WeatherApiService
import com.erneker.weather.database.getDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MyApplication : Application() {
    private val apiService: WeatherApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization/deserialization
            .build()

        retrofit.create(WeatherApiService::class.java)
    }

    val repository: Repository by lazy {
        Repository(apiService, getDatabase(this))
    }

    override fun onCreate() {
        super.onCreate()
    }
}