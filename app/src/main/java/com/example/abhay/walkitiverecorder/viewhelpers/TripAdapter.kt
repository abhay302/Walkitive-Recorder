package com.example.abhay.walkitiverecorder.viewhelpers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.models.Trip
import kotlinx.android.synthetic.main.layout_trip.view.*

class TripAdapter(val list: MutableList<Trip>) : RecyclerView.Adapter<TripAdapter.TripHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_trip, parent, false)
        return TripHolder(view)
    }

    override fun onBindViewHolder(holder: TripHolder, position: Int) {
        val trip = list[position]
        holder.name.text = trip.name
        holder.description.text = trip.description
        holder.spotsCount.text = (trip.spots?.size ?: 0).toString().plus(" spots")
        holder.duration.text = trip.duration.toString().plus(" mins")
        holder.totalDistance.text = trip.total_distance.toString().plus(" kms")
    }

    override fun getItemCount() = list.size
    class TripHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.trip_image
        val name: TextView = view.trip_name
        val description: TextView = view.trip_description
        val spotsCount: TextView = view.trip_spots_count
        val duration: TextView = view.trip_duration
        val totalDistance: TextView = view.trip_total_distance
    }
}