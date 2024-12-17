package com.davimoretti.horadocafe.ServiceAPI

import android.content.Context

data class AuthAPIRequest(
     val usuario: String,
     val senha: String
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