package com.lehi.calendario.presentation.models

data class UserEditProfileUIState(
    val nombreActual: String,
    val emailActual: String,
    val nuevoNombre: String,
    val contrasenaActual: String,
    val nuevaContrasena: String,
    val repetirNuevaContrasena: String,
    val mensajeError: String?,
    val perfilActualizado: Boolean
)
