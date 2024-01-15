package com.erneker.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erneker.weather.model.BeerSupplier

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BeerSupplierAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val supplierList = generateSupplierData()
        adapter = BeerSupplierAdapter(supplierList)
        recyclerView.adapter = adapter
    }

    private fun generateSupplierData(): List<BeerSupplier> {
        val suppliers = mutableListOf<BeerSupplier>()
        for (i in 1..1000) {
            suppliers.add(BeerSupplier("Beer Supplier $i", "Description of suplier $i"))
        }
        return suppliers
    }
}