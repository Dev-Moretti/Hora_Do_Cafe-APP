package com.davimoretti.horadocafe.ServiceAPI

import android.content.Context
import android.content.SharedPreferences


class TokenAPI(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("jwt_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("jwt_token", null)
    }
}