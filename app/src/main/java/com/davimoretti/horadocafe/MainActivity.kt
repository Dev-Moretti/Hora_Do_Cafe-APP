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

        val tokenRepository = TokenAPI(this)
        val retrofit = RetrofitClient.getClient("http://davicafeservices.ddns.net:5168/", tokenRepository)
        apiService = retrofit.create(EnderecoAPI::class.java)
        authManager = AuthManager(apiService, this)

        lifecycleScope.launch {
            val loggedIn = authManager.login("Davi", "DaviCafe@")
            if (loggedIn) {
                val cafes = apiService.getCafe("Bearer ${authManager.getToken()}")
                println(cafes)
            } else {
                println("Falha na autenticação.")
            }
        }

    }

    fun testeClick(view: View) {
       CoroutineScope(Dispatchers.IO).launch {

        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Chama a função suspensa usando CoroutineScope
        CoroutineScope(Dispatchers.Main).launch {
           // val cafes = getCafes() // Chama a função suspensa para obter os cafés
           // recyclerView.adapter = CafeAdapter(cafes)
        }
    }

<<<<<<< HEAD



=======
    private suspend fun getCafes(): List<Cafe> {

        val enderecoAPI = retrofit.create(EnderecoAPI::class.java)

        return try {
            val response = enderecoAPI.getCafe()

            if (response.isSuccessful && response.body() != null) {
                response.body()!! // Para retorno do tipo List<Cafe>
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_cafe", "Erro ao obter lista de cafés")
            emptyList()
        }
    }

    private suspend fun exibirCafe(cafe: Cafe?){

        val id = cafe?.id
        val nome = cafe?.nome
        val capPorDose = cafe?.capPorDose
        val tipo = cafe?.tipo
        val precoCap = cafe?.precoCap
        val imagemUrl = cafe?.imagemUrl
        val estoque = cafe?.estoque
        val intensidade = cafe?.intensidade
        val dataCadastro = cafe?.dataCadastro


//        val textNome: TextView = findViewById(R.id.TW_cafe)
        val textTipo: TextView = findViewById(R.id.TW_cafe)

//        textNome.text = nome
        textTipo.text = intensidade
>>>>>>> a54daa1825575ead276fbc3a8cf3c04e129384d7


//    private suspend fun getCafes(): List<Cafe> {
//
//        val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
//
//        return try {
//            val response = enderecoAPI.getCafe()
//
//            if (response.isSuccessful && response.body() != null) {
//                response.body()!! // Para retorno do tipo List<Cafe>
//            } else {
//                emptyList()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.i("info_cafe", "Erro ao obter lista de cafés")
//            emptyList()
//        }
//    }

}