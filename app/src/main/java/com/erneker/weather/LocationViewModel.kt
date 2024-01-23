package com.erneker.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erneker.weather.domain.Location
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class LocationViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _locationValue = MutableLiveData<Location>()
    val locationValue: LiveData<Location> = _locationValue

    val showHint = MutableLiveData<Boolean>()
    val showNotFound = MutableLiveData<Boolean>()
    val showProgressBar = MutableLiveData<Boolean>()
    val locationNameMutable = MutableLiveData<String?>()
    val processToDetail = MutableLiveData<Boolean>()

    private fun getLocation(name: String){
        viewModelScope.launch {
            try {
                _locationValue.value = repository.getLocation(name).firstOrNull()
                showProgressBar.value = false
            } catch (e: Exception) {
                Log.v("MYAPP", "Not found: " + e.message)

                // show not found message
                showNotFound.value = true
                showProgressBar.value = false
            }
        }
    }

    fun search () {
        if (locationNameMutable.value != null && locationNameMutable.value!!.isNotEmpty()) {
            // location name was provided by the user
            showProgressBar.value = true
            getLocation(locationNameMutable.value!!)
            processToDetail.value = true
        } else {
            // location was not provided, show hint text view
            showHint.value = true
        }
    }

    fun hideHintAndNotFound () {
        showHint.value = false
        showNotFound.value = false
    }
}