package com.davimoretti.horadocafe.ServiceAPI

import com.davimoretti.horadocafe.model.Cafe
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {

    @GET("Cafes/1")
    suspend fun getCafeId() : Response<Cafe>
}