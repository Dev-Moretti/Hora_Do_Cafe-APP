package com.davimoretti.horadocafe.ServiceAPI

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


data class AuthAPIRequest(
     val user: String = "Davi",
     val senha: String = "DaviCafe1825"
)

data class AuthAPIResponse(
     val token: String
)

class AuthManager(private val apiService: EnderecoAPI, context: Context) {
     private val tokenRepository = TokenAPI(context)

     suspend fun login(user: String, senha: String): Boolean {

          return try {
               val response = apiService.authenticate(AuthAPIRequest(user, senha))
               tokenRepository.saveToken(response.token)
               println("Token obtido: ${tokenRepository.getToken()}")
               true
          } catch (e: Exception) {
               e.printStackTrace()
               false
          }
     }

     fun getToken(): String? {
          return tokenRepository.getToken()
     }
}