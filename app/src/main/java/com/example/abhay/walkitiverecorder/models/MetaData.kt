package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName

data class MetaData(
        @SerializedName("total_pages")
        var total_pages: Int? = null,

        @SerializedName("total_spots")
        var total_spots: Int? = null
)