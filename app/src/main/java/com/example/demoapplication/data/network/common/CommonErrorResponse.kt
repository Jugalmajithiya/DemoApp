package com.example.demoapplication.data.network.common


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CommonErrorResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("code")
        val code: Int? = null,
        @SerializedName("message")
        val message: String? = null
    ) : Parcelable
}