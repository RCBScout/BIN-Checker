package com.vitaliymatr.binchecker.api

import com.vitaliymatr.binchecker.model.BinList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {
    @GET("{binNumber}")
    fun fetchContents(@Path("binNumber") binNumber: String): Call<BinList>
}