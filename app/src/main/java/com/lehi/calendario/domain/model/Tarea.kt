package com.lehi.calendario.domain.model

import java.time.LocalDateTime

enum class EstadoTarea {
    PENDIENTE,
    FINALIZADA,
    CANCELADA
}

enum class PrioridadTarea{
    BAJA, MEDIA, ALTA
}
data class Tarea(
    val id: Int,
    val usuario: Usuario,
    val titulo: String,
    val descripcion: String?,
    val fechaTarea: String,
    val estadoTarea: EstadoTarea,
    val prioridadTarea: PrioridadTarea
)