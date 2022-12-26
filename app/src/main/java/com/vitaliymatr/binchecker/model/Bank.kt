package com.vitaliymatr.binchecker.model


import com.google.gson.annotations.SerializedName

data class Bank (
    @SerializedName("name")
    var name: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("phone")
    var phone: String = "",
    @SerializedName("city")
    var city: String = "")
