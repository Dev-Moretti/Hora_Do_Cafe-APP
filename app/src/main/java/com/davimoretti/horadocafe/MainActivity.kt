package com.davimoretti.horadocafe

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davimoretti.horadocafe.ServiceAPI.EnderecoAPI
import com.davimoretti.horadocafe.ServiceAPI.RetrofitHelper
import com.davimoretti.horadocafe.model.Cafe
import com.davimoretti.horadocafe.model.CafeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    fun testeClick(view: View) {
//        CoroutineScope(Dispatchers.IO).launch {
//            getCafeId()
//        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Chama a função suspensa usando CoroutineScope
        CoroutineScope(Dispatchers.Main).launch {
            val cafes = getCafes() // Chama a função suspensa para obter os cafés
            recyclerView.adapter = CafeAdapter(cafes)
        }


    }

    private suspend fun getCafeId(){

        var retorno: Response<Cafe>? = null

        try {
            val enderecoAPI = retrofit.create( EnderecoAPI::class.java)
            retorno = enderecoAPI.getCafeId()

        }
        catch (e: Exception){
            e.printStackTrace()
            Log.i("info_cafe", "erro ao obter cafe")
        }

        if(retorno != null) {
            if(retorno.isSuccessful){
                val cafe = retorno.body()
                exibirCafe(cafe)
            }
        }
    }

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


        val exiCafe = "Cafe: $nome \n Tipo: $tipo ;"

        val text: TextView = findViewById(R.id.TW_cafe)
        text.text = exiCafe

    }

}