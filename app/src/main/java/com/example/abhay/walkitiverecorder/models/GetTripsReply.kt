package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName

data class GetTripsReply (
        @SerializedName("trips")
        var trips: Array<Trip>? = null,

        var meta: MetaData? = null
)