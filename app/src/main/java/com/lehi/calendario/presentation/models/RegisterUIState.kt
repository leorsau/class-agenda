package com.lehi.calendario.presentation.models

data class RegisterUIState(
    val nombre: String,
    val email: String,
    val contrasena: String,
    val repetirContrasena: String,
    val mensajeErrorCampos: String?,
    val mensajeErrorContrasena:String?,
    val mensajeErrorEmailFormat: String?,
    val registroCorrecto: Boolean
)
