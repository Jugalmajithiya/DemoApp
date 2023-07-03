package com.example.demoapplication.ui.home.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Results(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
) : Parcelable