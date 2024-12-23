package com.davimoretti.horadocafe.ServiceAPI

import com.davimoretti.horadocafe.model.Cafe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APICafeServices {

    @POST("api/Auth")
    suspend fun authenticate(@Body authAPIRequest: AuthAPIRequest): AuthAPIResponse


    @GET("api/Cafes/2")
    suspend fun getCafeId() : Response<Cafe>


    @GET("api/Cafes")
    suspend fun getCafe(): Response<List<Cafe>>

}