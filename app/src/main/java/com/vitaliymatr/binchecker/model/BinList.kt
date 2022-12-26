package com.vitaliymatr.binchecker.model


import com.google.gson.annotations.SerializedName

data class BinList (
    @SerializedName("number")
    var number: Number? = null,

    @SerializedName("scheme")
    var scheme: String = "",

    @SerializedName("type")
    var type: String = "",

    @SerializedName("brand")
    var brand: String = "",

    @SerializedName("prepaid")
    var prepaid: Boolean? = null,

    @SerializedName("country")
    var country: Country? = null,

    @SerializedName("bank")
    var bank: Bank? = null

)

