package com.lehi.calendario.domain.model

import java.time.LocalDateTime

data class Evento (
    val id: Int,
    val usuario: Usuario,
    val titulo: String,
    val descripcion: String?,
    val fechaInicioEvento: String,
    val fechaFinEvento: String
)