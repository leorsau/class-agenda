package com.lehi.calendario.presentation.models

data class LoginUIState(
    val email: String,
    val contrasena: String,
    val mensajeError:String?,
    val loginCorrecto: Boolean
)
