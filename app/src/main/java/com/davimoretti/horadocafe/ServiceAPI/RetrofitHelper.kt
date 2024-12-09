package com.davimoretti.horadocafe.ServiceAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.3.200:5168/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}