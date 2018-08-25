package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName
import java.io.File

data class Spot(
        @SerializedName("id")
        var id: Int? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("address")
        var address: String? = null,

        @SerializedName("category_id")
        var category_id: Int? = null,

        @SerializedName("latitude")
        var latitude: Float? = null,

        @SerializedName("longitude")
        var longitude: Float? = null,

        @SerializedName("image_attachments_attributes")
        var image_attachments_attributes: Array<File>? = null,

        @SerializedName("audio_attachments_attributes")
        var audio_attachments_attributes: Array<File>? = null
)