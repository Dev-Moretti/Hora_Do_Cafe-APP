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
val dataCadastro: LocalDateTime
)