package com.lehi.calendario.presentation.models

sealed interface RegisterUIEvent {
    data class NombreCambiado(val nuevoNombre: String) : RegisterUIEvent
    data class EmailCambiado(val nuevoEmail: String) : RegisterUIEvent
    data class ContrasenaCambiada(val nuevaContrasena: String) : RegisterUIEvent
    data class RepetirContrasenaCambiada(val nuevaRepetirContrasena: String) : RegisterUIEvent
    data object CrearCuentaClick : RegisterUIEvent
}