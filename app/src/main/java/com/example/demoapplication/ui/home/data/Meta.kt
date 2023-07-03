package com.example.demoapplication.ui.home.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Meta(
    @SerializedName("disclaimer")
    val disclaimer: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("license")
    val license: String?,
    @SerializedName("results")
    val results: Results?,
    @SerializedName("terms")
    val terms: String?
) : Parcelable