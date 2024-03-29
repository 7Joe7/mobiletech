package com.erneker.weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erneker.weather.domain.Location

@Entity(tableName = "location")
data class LocationDTO (
    @PrimaryKey
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?,
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val visibility: Int,
    val timestamp: String
)

fun LocationDTO.mapToDomain(): Location {
    return Location(
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        country = this.country,
        state = this.state,
        temp = this.temp,
        pressure = this.pressure,
        humidity = this.humidity,
        windSpeed = this.windSpeed,
        visibility = this.visibility,
        timestamp = this.timestamp
    )
}