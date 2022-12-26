package com.vitaliymatr.binchecker.model


import com.google.gson.annotations.SerializedName




data class Country (
    @SerializedName("numeric")
    val numeric: String = "",

    @SerializedName("alpha2")
    val alpha2: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("emoji")
    val emoji: String = "",

    @SerializedName("currency")
    val currency: String = "",

    @SerializedName("latitude")
    val latitude: Int? = null,

    @SerializedName("longitude")
    val longitude: Int? = null
)