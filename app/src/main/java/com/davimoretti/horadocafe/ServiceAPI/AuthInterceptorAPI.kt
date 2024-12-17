package com.davimoretti.horadocafe.ServiceAPI

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptorAPI(private val tokenRepository: TokenAPI) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = tokenRepository.getToken()
        return if (token != null) {
            val modifiedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(modifiedRequest)
        } else {
            chain.proceed(request)
        }
    }

}