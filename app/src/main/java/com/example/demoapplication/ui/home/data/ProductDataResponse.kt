package com.example.demoapplication.ui.home.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ProductDataResponse(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("results")
    val results: List<Result?>?
) : Parcelable