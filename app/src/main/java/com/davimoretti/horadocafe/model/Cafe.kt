package com.davimoretti.horadocafe.model

import java.time.LocalDateTime

data class Cafe(

val id: Int,
val nome: String,
val capPorDose: Int,
val tipo: String,
val precoCap: Double,
val imagemUrl: String,
val estoque: Double,
val intensidade: String,
val dataCadastro: LocalDateTime,


)

/*
"id": 0,
"nome": "string",
"capPorDose": 1,
"tipo": "string",
"precoCap": 0,
"imagemUrl": "string",
"estoque": 0,
"intensidade": "string",
"dataCadastro": "2024-11-28T21:10:32.980Z"
*/



