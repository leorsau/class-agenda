package com.lehi.calendario.presentation.models

enum class TipoItem{
    Tarea,Evento
}

data class HomeItem(
    val id: Int,
    val titulo: String,
    val descripcion: String?=null,
    val fechaInicio: String,
    val fechaFin:String?=null,
    val horaInicio: String?=null,
    val horaFin: String?=null,
    val tipo: TipoItem
    )