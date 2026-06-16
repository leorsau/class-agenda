package com.lehi.calendario.presentation.models

sealed interface UserEditProfileUIEvent {
    data class NuevoNombreCambiado(val nuevoNombre: String) : UserEditProfileUIEvent
    data class ContrasenaActualCambiada(val contrasenaActual: String) : UserEditProfileUIEvent
    data class NuevaContrasenaCambiada(val nuevaContrasena: String) : UserEditProfileUIEvent
    data class RepetirNuevaContrasenaCambiada(val repetirNuevaContrasena: String) : UserEditProfileUIEvent
    data object GuardarClick : UserEditProfileUIEvent

}