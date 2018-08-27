package com.example.abhay.walkitiverecorder.api

import com.example.abhay.walkitiverecorder.models.GetTripsReply
import com.example.abhay.walkitiverecorder.models.Spot
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("spots")
    fun addSpot(@Body spot: Spot): Call<JsonElement>

    @GET("trips")
    fun getTrips(@Query("page") page: Int): Call<GetTripsReply>
}