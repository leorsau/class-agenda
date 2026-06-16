package com.lehi.calendario.presentation.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed interface EventDetailsUIEvent {
    data class TituloCambiado(val nuevoTitulo: String) : EventDetailsUIEvent
    data class DescripcionCambiada(val nuevaDescripcion: String) : EventDetailsUIEvent
    data class FechaInicioCambiada(val nuevaFechaInicio: LocalDate) : EventDetailsUIEvent
    data class HoraInicioCambiada(val nuevaHoraInicio: LocalTime) : EventDetailsUIEvent
    data class FechaFinCambiada(val nuevaFechaFin: LocalDate) : EventDetailsUIEvent
    data class HoraFinCambiada(val nuevaHoraFin: LocalTime) : EventDetailsUIEvent
    data object EditarClick : EventDetailsUIEvent
    data object GuardarClick : EventDetailsUIEvent
    data object BorrarClick : EventDetailsUIEvent

}