package com.lehi.calendario.presentation.models

import java.time.LocalDateTime


enum class TipoItem {
    TAREA, EVENTO
}

enum class FiltroHome {
    TODOS, TAREAS, EVENTOS
}

data class HomeItem(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val fechaInicio: String,
    val fechaFin: String? = null,
    val horaInicio: String? = null,
    val horaFin: String? = null,
    val tipo: TipoItem,
    val fechaOrden: LocalDateTime
)