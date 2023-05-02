package com.example.timeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityTimeAdapter (
    var myWorldTimeClass: ArrayList<WorldTimeClass>)
    : RecyclerView.Adapter<CityTimeAdapter.CountryViewHolder>() {

    var onItemClick: ((WorldTimeClass) -> Unit)? = null

    class CountryViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val textCity: TextView = itemview.findViewById(R.id.tvCityName)
        val textCountry: TextView = itemview.findViewById(R.id.tvCountryName)
        val textTime: TextView = itemview.findViewById(R.id.tvClockTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CountryViewHolder(view)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = myWorldTimeClass[position]
        holder.textCity.text = country.nameCity
        holder.textCountry.text = country.nameCountry
        holder.textTime.text = country.countryTime

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(country)
        }
    }

    override fun getItemCount(): Int {
        return myWorldTimeClass.size
    }


}