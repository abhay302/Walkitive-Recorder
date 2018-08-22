package com.example.abhay.walkitiverecorder.controllers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.abhay.walkitiverecorder.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_display_location.*
import kotlinx.android.synthetic.main.toolbar_add_address.view.*
import java.io.IOException
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DisplayLocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_display_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ContextCompat.checkSelfPermission(activity!!.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        } else {
            initializeMap()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100) {
            if (ContextCompat.checkSelfPermission(activity!!.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            } else {
                initializeMap()
            }
        }
    }

    private fun initializeMap() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!! as Activity)

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_view_map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (ContextCompat.checkSelfPermission(activity!!.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                val position = LatLng(location.latitude, location.longitude)
                googleMap?.addMarker(MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.add_spot_marker))
                        .title("You are here")
                        .position(position))
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 18.0f))

                button_go_to_current_location.setOnClickListener {
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 18.0f))
                }

                googleMap?.setOnCameraIdleListener {
                    getAddressFromLocation(googleMap.cameraPosition.target)
                }
            }
        } else {            //TODO work

        }
    }

    private fun getAddressFromLocation(location: LatLng) {

        val geocoder = Geocoder(activity, Locale.getDefault())


        try {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

            if (addresses.size > 0) {
                val fetchedAddress = addresses[0]
                fetchedAddress.getAddressLine(0)
                Log.d("Fetched address", fetchedAddress.toString())
                val strAddress = StringBuilder()
                for (i in 0..fetchedAddress.maxAddressLineIndex) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append(" ")
                }
                add_spot_toolbar.display__current_location_address.text = strAddress

            } else {
                Toast.makeText(activity, "Address not found", Toast.LENGTH_LONG).show()
            }

        } catch (e: IOException) {
            Toast.makeText(activity, "Could not get address..!", Toast.LENGTH_LONG).show()
        }

    }
}