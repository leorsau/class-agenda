package com.lehi.calendario.presentation.models

data class LoginUIState(
    val email: String,
    val contrasena: String,
    val mensajeErrorCamposVacios:String?,
    val mensajeErrorEmailContrasena: String?,
    val loginCorrecto: Boolean
)
