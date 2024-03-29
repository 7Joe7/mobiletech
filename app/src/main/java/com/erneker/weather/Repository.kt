package com.erneker.weather

import android.util.Log
import com.erneker.weather.api.WeatherApiService
import com.erneker.weather.api.LocationNetwork
import com.erneker.weather.api.mapToDatabase
import com.erneker.weather.database.MyRoomDatabase
import com.erneker.weather.database.mapToDomain
import com.erneker.weather.domain.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class Repository (
    private val apiService: WeatherApiService,
    private val database: MyRoomDatabase
    ) {

    suspend fun getLocation (name: String) : Flow<Location> {
        // get data from REST API and cache them to DB
        refreshLocations(name)

        // return FLOW of Location from database to ViewModel
        return database.locationDao.getLocationByName(name).map { it.mapToDomain() }
    }

    private suspend fun refreshLocations(name: String) {
        try {
            // call REST API service to response
            val apiResponse = apiService.getLocation(name)
            // parse locationNetworkList from response body
            val locationNetworkList: List<LocationNetwork>? = apiResponse.body()

            if (!locationNetworkList.isNullOrEmpty()) {
                val locationNetwork = locationNetworkList[0] // expect one

                val apiForecastResponse = apiService.getForecast(locationNetwork.lat, locationNetwork.lon).body()

                // convert network model from REST API to DB entity
                val locationDTO = locationNetwork.mapToDatabase(apiForecastResponse!!.list[0])

                // save to Room database
                withContext(Dispatchers.IO) {
                    database.locationDao.insert(locationDTO)
                }
            } else {
                // no data found on REST API
                Log.v("MYAPP", "Not found on REST API")
            }
        } catch (e: Exception) {
            // Handle PI call errors
            Log.e("MYAPP", "Error refreshing locations " + e.localizedMessage + ", " + e.message + ", " + e.stackTrace)
        }
    }
}