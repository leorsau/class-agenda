package com.lehi.calendario.presentation.models

sealed interface LoginUIEvent {
    data class EmailCambiado(val nuevoEmail: String): LoginUIEvent
    data class ContrasenaCambiada(val nuevaContrasena: String): LoginUIEvent
    data object  EntrarClick: LoginUIEvent
}