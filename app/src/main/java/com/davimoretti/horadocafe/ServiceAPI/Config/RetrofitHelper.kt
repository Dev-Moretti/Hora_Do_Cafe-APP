package com.davimoretti.horadocafe.ServiceAPI.Config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String, tokenRepository: TokenAPI): Retrofit {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptorAPI(tokenRepository))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("http://davicafeservices.ddns.net:5168/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}