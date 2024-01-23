package com.erneker.weather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    companion object {
        const val API_KEY = "6df3667a58f91ce640c2841e6caf5a76"
        const val GEOLOCATION_ENDPOINT = "geo/1.0/direct"
        const val FORECAST_ENDPOINT = "data/2.5/forecast"
    }

    @GET(GEOLOCATION_ENDPOINT)
    suspend fun getLocation (
        @Query("q") q: String,
        @Query("appid") appid: String = API_KEY,
        @Query("limit") limit: Int = 1
    ) : Response<List<LocationNetwork>>

    @GET(FORECAST_ENDPOINT)
    suspend fun getForecast (
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = API_KEY
    ) : Response<ForecastNetwork>
}