package com.davimoretti.horadocafe

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davimoretti.horadocafe.ServiceAPI.AuthManager
import com.davimoretti.horadocafe.ServiceAPI.EnderecoAPI
import com.davimoretti.horadocafe.ServiceAPI.RetrofitClient
import com.davimoretti.horadocafe.ServiceAPI.TokenAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: EnderecoAPI
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //parte nova com chat gpt
    }

    fun testeClick(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
        }

        val tokenRepository = TokenAPI(this)
        val retrofit = RetrofitClient.getClient("http://davicafeservices.ddns.net:5168/", tokenRepository)

        apiService = retrofit.create(EnderecoAPI::class.java)
        authManager = AuthManager(apiService, this)

        lifecycleScope.launch {
            val loggedIn = authManager.login("Davi", "DaviCafe1825")
            if (loggedIn) {
                val token = authManager.getToken()
                println("meu token = $token")
                val cafes = apiService.getCafe("Bearer $token")
                println(cafes)
            } else {
                println("Falha na autenticação.")
            }
        }



//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Chama a função suspensa usando CoroutineScope
//        CoroutineScope(Dispatchers.Main).launch {
//            // val cafes = getCafes() // Chama a função suspensa para obter os cafés
//            // recyclerView.adapter = CafeAdapter(cafes)
//        }
    }


}