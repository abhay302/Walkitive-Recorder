package com.example.abhay.walkitiverecorder.api

import com.example.abhay.walkitiverecorder.models.Spot
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("spots")
    fun addSpot(@Body spot: Spot): Call<JsonElement>
}