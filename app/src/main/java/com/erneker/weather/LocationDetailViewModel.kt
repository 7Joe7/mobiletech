package com.erneker.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erneker.weather.domain.Location
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class LocationDetailViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _locationValue = MutableLiveData<Location>()
    val locationValue: LiveData<Location> = _locationValue
    val processToHome = MutableLiveData<Boolean>()

    fun getLocation(name: String){
        viewModelScope.launch {
            try {
                _locationValue.value = repository.getLocation(name).firstOrNull()
            } catch (e: Exception) {
                Log.v("MYAPP", "Not found: " + e.message)
            }
        }
    }

    fun back () {
        Log.i("INFO", "something")
        processToHome.value = true
    }

    fun onBackNavigated() {
        processToHome.value = false
    }
}