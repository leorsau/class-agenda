package com.lehi.calendario.domain.model

import java.time.LocalDate


enum class EstadoTarea {
    PENDIENTE,
    FINALIZADA
}

enum class PrioridadTarea{
    BAJA, MEDIA, ALTA
}
data class Tarea(
    val id: Int,
    val usuario: Usuario,
    val titulo: String,
    val descripcion: String?,
    val fechaTarea: LocalDate,
    val estadoTarea: EstadoTarea,
    val prioridadTarea: PrioridadTarea
)