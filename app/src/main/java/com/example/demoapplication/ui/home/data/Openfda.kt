package com.example.demoapplication.ui.home.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Openfda(
    @SerializedName("application_number")
    val applicationNumber: List<String?>?,
    @SerializedName("brand_name")
    val brandName: List<String?>?,
    @SerializedName("generic_name")
    val genericName: List<String?>?,
    @SerializedName("manufacturer_name")
    val manufacturerName: List<String?>?,
    @SerializedName("nui")
    val nui: List<String?>?,
    @SerializedName("original_packager_product_ndc")
    val originalPackagerProductNdc: List<String?>?,
    @SerializedName("package_ndc")
    val packageNdc: List<String?>?,
    @SerializedName("pharm_class_cs")
    val pharmClassCs: List<String?>?,
    @SerializedName("pharm_class_epc")
    val pharmClassEpc: List<String?>?,
    @SerializedName("pharm_class_moa")
    val pharmClassMoa: List<String?>?,
    @SerializedName("product_ndc")
    val productNdc: List<String?>?,
    @SerializedName("product_type")
    val productType: List<String?>?,
    @SerializedName("route")
    val route: List<String?>?,
    @SerializedName("rxcui")
    val rxcui: List<String?>?,
    @SerializedName("spl_id")
    val splId: List<String?>?,
    @SerializedName("spl_set_id")
    val splSetId: List<String?>?,
    @SerializedName("substance_name")
    val substanceName: List<String?>?,
    @SerializedName("unii")
    val unii: List<String?>?
) : Parcelable