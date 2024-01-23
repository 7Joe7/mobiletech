package com.erneker.weather.domain

data class Location(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
)

data class Locations(val locations: List<Location>)