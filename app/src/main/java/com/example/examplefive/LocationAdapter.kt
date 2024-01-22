package com.erneker.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erneker.weather.model.Location

class LocationAdapter(private val locationList: List<Location>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beer_supplier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplier = locationList[position]
        holder.supplierNameTextView.text = supplier.name
        holder.supplierDescriptionTextView.text = supplier.description
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val supplierNameTextView: TextView = itemView.findViewById(R.id.supplierName)
        val supplierDescriptionTextView: TextView = itemView.findViewById(R.id.supplierDescription)
    }
}