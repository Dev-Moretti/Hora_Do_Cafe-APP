package com.davimoretti.horadocafe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davimoretti.horadocafe.ServiceAPI.AuthManager
import com.davimoretti.horadocafe.ServiceAPI.APICafeServices
import com.davimoretti.horadocafe.ServiceAPI.Config.RetrofitClient
import com.davimoretti.horadocafe.ServiceAPI.Config.TokenAPI
import com.davimoretti.horadocafe.model.Cafe
import com.davimoretti.horadocafe.model.CafeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: APICafeServices
    private lateinit var authManager: AuthManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var cafeAdapter: CafeAdapter
    var conectado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvCafes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cafeAdapter = CafeAdapter(emptyList())
        recyclerView.adapter = cafeAdapter

        lifecycleScope.launch {
           conectado = loginAPI()
            mostrarStatusConexao(findViewById(R.id.main), conectado)
            exibirCafes(conectado)
        }
    }

    private suspend fun loginAPI() : Boolean {
        val tokenRepository = TokenAPI(this)
        val retrofit = RetrofitClient.getClient("http://davicafeservices.ddns.net:5168/", tokenRepository)

        apiService = retrofit.create(APICafeServices::class.java)
        authManager = AuthManager(apiService, this)

        val loggedIn = authManager.loginUser(
            "Davi",
            "DaviCafe1825",
            "a422c41-ea637f-93216f3-34cf3a9-3e5fe20-65ba6a"
        )

        if (loggedIn) {
            println("Logado com sucesso!")
            return true
        } else {
            println("Falha na autenticação!")
            return false
        }
    }

    private fun mostrarStatusConexao(view: View, conectado: Boolean) {
        val message = if (conectado) "Conectado com sucesso"
        else "Desconectado"

        val snackbar = Snackbar.make(
            findViewById(android.R.id.content), // View raiz da Activity
            message,
            Snackbar.LENGTH_SHORT // Duração curta (1-2 segundos)
        )
        snackbar.setBackgroundTint(
            if (conectado) getColor(R.color.snackbar_connected) // Cor personalizada para sucesso
            else getColor(R.color.snackbar_disconnected) // Cor personalizada para erro
        )
        snackbar.show()
    }

    private fun exibirCafes(conectado: Boolean){
        lifecycleScope.launch {
            if (conectado) {
                try {
                    val response = apiService.getCafe()
                    if (response.isSuccessful) {
                        val cafes = response.body()
                        cafes?.let {
                            cafeAdapter.updateCafes(it) // Atualiza a RecyclerView
                        }
                    } else {
                        Log.e("API_ERROR", "Erro: ${response.code()} - ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("NETWORK_ERROR", "Erro na requisição: ${e.message}")
                }
            }
        }
    }

}