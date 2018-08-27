package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName

data class Trip (
        @SerializedName("id")
        var id: Int? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("total_distance")
        var total_distance:Int? = null,

        @SerializedName("duration")
        var duration:Int? = null,

        @SerializedName("spots")
        var spots: Array<Spot>? =null
)