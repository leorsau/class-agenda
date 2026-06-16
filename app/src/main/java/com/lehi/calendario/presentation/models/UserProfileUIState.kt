package com.lehi.calendario.presentation.models

data class UserProfileUIState(
    val nombre:String,
    val email:String,
    val tareasCompletadas: Int,
    val tareasPendientes: Int,
    val eventosProximos: Int,
    val sesionCerrada: Boolean
)
