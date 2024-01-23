package com.erneker.weather.api

import com.erneker.weather.database.LocationDTO

data class LocationNetwork(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
)

fun LocationNetwork.mapToDatabase(): LocationDTO {
    return LocationDTO(
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        country = this.country,
        state = this.state
    )
}