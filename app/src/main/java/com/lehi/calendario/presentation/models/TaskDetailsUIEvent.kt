package com.lehi.calendario.presentation.models

import com.lehi.calendario.domain.model.EstadoTarea
import com.lehi.calendario.domain.model.PrioridadTarea
import java.time.LocalDate

sealed interface TaskDetailsUIEvent {
    data class TituloCambiado(val nuevoTitulo: String) : TaskDetailsUIEvent
    data class FechaCambiada(val nuevaFecha: LocalDate) : TaskDetailsUIEvent
    data class DescripcionCambiada(val nuevaDescripcion: String) : TaskDetailsUIEvent
    data class PrioridadCambiada(val nuevaPrioridad: PrioridadTarea) : TaskDetailsUIEvent
    data class EstadoCambiado(val nuevoEstado: EstadoTarea) : TaskDetailsUIEvent
    data object EditarClick : TaskDetailsUIEvent
    data object GuardarClick : TaskDetailsUIEvent
    data object BorrarClick : TaskDetailsUIEvent

}