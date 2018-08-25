package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName

data class GetSpotsReply(
        @SerializedName("spots")
        var spots: Array<Spot>? = null,

        var meta: MetaData? = null
)