package com.erneker.weather.api

import com.erneker.weather.database.LocationDTO

data class LocationNetwork(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
)

fun LocationNetwork.mapToDatabase(forecast: Forecast): LocationDTO {
    return LocationDTO(
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        country = this.country,
        state = this.state,
        temp = kelvinToCelsius(forecast.main.temp),
        pressure = forecast.main.pressure,
        humidity = forecast.main.humidity,
        windSpeed = forecast.wind.speed,
        visibility = forecast.visibility,
        timestamp = forecast.dt_txt
    )
}

fun kelvinToCelsius(kelvin: Double): Double {
    return kelvin - 273.15
}