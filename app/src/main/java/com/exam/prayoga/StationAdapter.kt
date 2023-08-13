package com.exam.prayoga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(private var stationList: List<Station>) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
        return StationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val currentStation = stationList[position]
        holder.bind(currentStation)
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    inner class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(station: Station) {
            itemView.findViewById<ImageView>(R.id.imageViewStation).setImageResource(station.imageResId)
            itemView.findViewById<TextView>(R.id.textViewStationName).text = station.name
            itemView.findViewById<TextView>(R.id.textViewStationCityName).text = station.cityname
            itemView.findViewById<TextView>(R.id.textViewStationDescription).text = station.description
        }
    }

    fun searchList(DataStations: List<Station>){
        stationList = DataStations
        notifyDataSetChanged()
    }
}

