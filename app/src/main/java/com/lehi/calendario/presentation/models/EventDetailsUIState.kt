package com.lehi.calendario.presentation.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class EventDetailsUIState(
    val id: Int?,
    val titulo: String,
    val descripcion: String,
    val fechaInicioEvento: LocalDate,
    val horaInicioEvento: LocalTime,
    val fechaFinEvento: LocalDate,
    val horaFinEvento: LocalTime,
    val modoEdicion: Boolean,
    val mensajeErrorTitulo: String?,
    val mensajeErrorFechaFin: String?,
    val eventoBorrado: Boolean
)
