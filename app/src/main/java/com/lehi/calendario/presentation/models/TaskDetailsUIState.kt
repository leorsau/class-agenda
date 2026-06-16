package com.lehi.calendario.presentation.models

import com.lehi.calendario.domain.model.EstadoTarea
import com.lehi.calendario.domain.model.PrioridadTarea
import java.time.LocalDate

data class TaskDetailsUIState(
    val id: Int?,
    val titulo: String,
    val descripcion: String,
    val fechaTarea: LocalDate,
    val estadoTarea: EstadoTarea,
    val prioridadTarea: PrioridadTarea,
    val modoEdicion: Boolean,
    val mensajeError: String?,
    val tareaBorrada: Boolean

)
