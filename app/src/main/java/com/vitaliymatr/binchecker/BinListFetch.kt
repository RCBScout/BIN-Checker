package com.vitaliymatr.binchecker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitaliymatr.binchecker.api.BinlistApi
import com.vitaliymatr.binchecker.model.BinList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "BinListFetch"

class BinListFetch {
    private val binListApi: BinlistApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        binListApi = retrofit.create(BinlistApi::class.java)
    }

    fun fetchContents(binNumber: String): LiveData<BinList> {
        val responseLiveData: MutableLiveData<BinList> = MutableLiveData()
        val binListRequest: Call<BinList> = binListApi.fetchContents(binNumber)

        binListRequest.enqueue(object : Callback<BinList> {
            override fun onFailure(call: Call<BinList>, t: Throwable) {
                Log.e(TAG, "Failed to fetch", t)
            }

            override fun onResponse(
                call: Call<BinList>,
                response: Response<BinList>
            ) {
                Log.d(TAG, "Response received $binNumber")
                val binList: BinList? = response.body()
                responseLiveData.value = binList
            }
        })

        return responseLiveData
    }
}
