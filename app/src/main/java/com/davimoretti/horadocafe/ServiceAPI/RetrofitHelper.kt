package com.davimoretti.horadocafe.ServiceAPI

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

<<<<<<< HEAD


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


//class RetrofitHelper {
//    companion object {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://davicafeservices.ddns.net:5168/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//}
=======
class RetrofitHelper {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://davicafeservices.ddns.net:5168/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
>>>>>>> a54daa1825575ead276fbc3a8cf3c04e129384d7
