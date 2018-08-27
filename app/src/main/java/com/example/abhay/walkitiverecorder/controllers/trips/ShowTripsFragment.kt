package com.example.abhay.walkitiverecorder.controllers.trips


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.api.RetrofitClient
import com.example.abhay.walkitiverecorder.models.GetTripsReply
import com.example.abhay.walkitiverecorder.models.Trip
import com.example.abhay.walkitiverecorder.viewhelpers.TripAdapter
import kotlinx.android.synthetic.main.fragment_show_trips.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowTripsFragment : Fragment() {
    lateinit var list: MutableList<Trip>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_trips, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTripsInCurrentPage()
    }

    private fun getTripsInCurrentPage(page: Int = 1) {
        val call = RetrofitClient.client.getTrips(page)

        call.enqueue(object : Callback<GetTripsReply> {

            override fun onResponse(call: Call<GetTripsReply>?, response: Response<GetTripsReply>?) {
                if(response?.code() ==200) {
                    list = response.body()?.trips?.toMutableList() ?: mutableListOf()
                    populateList()
                }
            }

            override fun onFailure(call: Call<GetTripsReply>?, t: Throwable) {

            }

        })
    }

    private fun populateList() {
        recyclerView = activity!!.display_trips_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = TripAdapter(list)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}