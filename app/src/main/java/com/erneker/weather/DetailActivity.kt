package com.erneker.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.erneker.weather.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: LocationDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        val app = application as MyApplication
        viewModel = ViewModelProvider(this, LocationViewModelFactory(app.repository))[LocationDetailViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Get the passed location name
        val locationName = intent.getStringExtra("LOCATION_NAME").toString()
        viewModel.locationValue.observe(this) { location ->
            binding.textLocation.text = "${location.name} (${location.country})"
            binding.textLatLon.text = "Latitude: ${viewModel.locationValue.value?.lat.toString()},  longitude: ${viewModel.locationValue.value?.lon.toString()}"
            binding.textTimestamp.text = "Date: ${location.timestamp}"
            binding.textTemp.text = "Temperature: ${location.temp.toString()}"
            binding.textHumidity.text = "Humidity: ${location.humidity.toString()}"
            binding.textPressure.text = "Pressure: ${location.pressure.toString()}"
            binding.textWindSpeed.text = "Wind Speed: ${location.windSpeed.toString()}"
            binding.textVisibility.text = "Visibility: ${location.visibility.toString()}"
        }
        viewModel.getLocation(locationName)

        viewModel.processToHome.observe(this) { value ->
            if (value) {
                finish()
                viewModel.onBackNavigated() // Reset the navigation event
            }
        }
    }
}