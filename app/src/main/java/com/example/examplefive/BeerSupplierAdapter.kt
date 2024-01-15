package com.erneker.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erneker.weather.model.BeerSupplier

class BeerSupplierAdapter(private val supplierList: List<BeerSupplier>) :
    RecyclerView.Adapter<BeerSupplierAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beer_supplier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplier = supplierList[position]
        holder.supplierNameTextView.text = supplier.name
        holder.supplierDescriptionTextView.text = supplier.description
    }

    override fun getItemCount(): Int {
        return supplierList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val supplierNameTextView: TextView = itemView.findViewById(R.id.supplierName)
        val supplierDescriptionTextView: TextView = itemView.findViewById(R.id.supplierDescription)
    }
}