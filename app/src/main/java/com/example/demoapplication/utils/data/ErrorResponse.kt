package com.example.demoapplication.utils.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("result") var status: Int,
    @SerializedName("msg") var message: String,
)