package com.davimoretti.horadocafe.ServiceAPI

import android.content.Context
import com.davimoretti.horadocafe.ServiceAPI.Config.TokenAPI


data class AuthAPIRequest(
     val user: String,
     val senha: String,
     val secretCode: String
)

data class AuthAPIResponse(
     val token: String
)

class AuthManager(private val apiService: APICafeServices, context: Context) {
     private val tokenRepository = TokenAPI(context)

     suspend fun loginUser(user: String, senha: String, secretCode: String): Boolean {

          return try {
               val response = apiService.authenticate(AuthAPIRequest(user, senha, secretCode))
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