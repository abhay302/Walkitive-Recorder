package com.example.abhay.walkitiverecorder.models

import com.google.gson.annotations.SerializedName

data class ErrorReply(

        @SerializedName("errors")
        var errors: Array<String>? = null
)