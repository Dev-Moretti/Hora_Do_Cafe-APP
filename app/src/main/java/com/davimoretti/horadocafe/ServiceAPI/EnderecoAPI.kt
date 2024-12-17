package com.davimoretti.horadocafe.ServiceAPI

import com.davimoretti.horadocafe.model.Cafe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface EnderecoAPI {

    @POST("api/Auth")
    suspend fun authenticate(@Body authAPIRequest: AuthAPIRequest): AuthAPIResponse


    @GET("api/Cafes/2")
    suspend fun getCafeId(@Header("Authorization") token: String) : Response<Cafe>


    @GET("api/Cafes")
    suspend fun getCafe(@Header("Authorization") token: String) : Response<List<Cafe>>

}