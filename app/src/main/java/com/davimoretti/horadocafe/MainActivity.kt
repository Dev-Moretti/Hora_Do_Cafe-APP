package com.davimoretti.horadocafe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.davimoretti.horadocafe.ServiceAPI.EnderecoAPI
import com.davimoretti.horadocafe.ServiceAPI.RetrofitHelper
import com.davimoretti.horadocafe.model.Cafe
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
        CoroutineScope(Dispatchers.IO).launch {
            getCafeId()
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