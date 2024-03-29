package com.erneker.weather.domain

data class Location(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?,
    val temp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val windSpeed: Double?,
    val visibility: Int?,
    val timestamp: String?
)
