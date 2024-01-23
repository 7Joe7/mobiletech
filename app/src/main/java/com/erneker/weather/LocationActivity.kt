package com.erneker.weather

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.erneker.weather.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding
    private lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        binding = ActivityLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, LocationViewModelFactory(app.repository))[LocationViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observing change of locationNameMutable property for hiding of Hint text
        viewModel.locationNameMutable.observe(this) { locationNameMutable ->
            if (!locationNameMutable.isNullOrEmpty()) {
                // if location name is not null or empty value
                viewModel.hideHintAndNotFound() // hide the hint text
            }
        }

        // Observing change of locationNameMutable property for hiding of Hint text
        viewModel.processToDetail.observe(this) { value ->
            if (value) {
                // go to detail activity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("MYDATA", viewModel.locationNameMutable.value)
                startActivity(intent)
                viewModel.processToDetail.value = false
            }
        }
    }
}