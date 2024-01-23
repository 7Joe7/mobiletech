package com.erneker.weather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    companion object {
        const val API_KEY = "6df3667a58f91ce640c2841e6caf5a76"
        const val GEOLOCATION_ENDPOINT = "geo/1.0/direct"
    }

    @GET(GEOLOCATION_ENDPOINT)
    suspend fun getLocation (
        @Query("q") q: String,
        @Query("appid") appid: String = API_KEY,
        @Query("limit") limit: Int = 1
    ) : Response<List<LocationNetwork>>
}