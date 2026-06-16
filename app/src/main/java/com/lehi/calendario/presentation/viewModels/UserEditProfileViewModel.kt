package com.lehi.calendario.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.UsuariosFake
import com.lehi.calendario.domain.model.Usuario
import com.lehi.calendario.presentation.models.UserEditProfileUIEvent
import com.lehi.calendario.presentation.models.UserEditProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserEditProfileViewModel: ViewModel() {
    private val usuarioActual= SessionUsuario.obtenerUsuarioActual()

    private val _uiState= MutableStateFlow(
        UserEditProfileUIState(
            nombreActual = usuarioActual?.nombre ?: "",
            emailActual = usuarioActual?.email ?: "",
            nuevoNombre = "",
            contrasenaActual = usuarioActual?.contrasena ?:"",
            nuevaContrasena = "",
            repetirNuevaContrasena = "",
            mensajeError = null,
            perfilActualizado = false
        )
    )

    val uiState: StateFlow<UserEditProfileUIState> = _uiState

    fun onEvent(event: UserEditProfileUIEvent) {
        when (event) {
            is UserEditProfileUIEvent.NuevoNombreCambiado -> cambiarNuevoNombre(event.nuevoNombre)
            is UserEditProfileUIEvent.ContrasenaActualCambiada -> cambiarContrasenaActual(event.contrasenaActual)
            is UserEditProfileUIEvent.NuevaContrasenaCambiada -> cambiarNuevaContrasena(event.nuevaContrasena)
            is UserEditProfileUIEvent.RepetirNuevaContrasenaCambiada -> cambiarRepetirNuevaContrasena(event.repetirNuevaContrasena)
            is UserEditProfileUIEvent.GuardarClick -> guardarCambios()

        }
    }

    private fun cambiarNuevoNombre(nuevoNombre: String){
        _uiState.value=_uiState.value.copy(
            nuevoNombre=nuevoNombre,
            mensajeError = null,
        )
    }

    private fun cambiarContrasenaActual(contrasenaActual: String) {
        _uiState.value = _uiState.value.copy(
            contrasenaActual = contrasenaActual,
            mensajeError = null,
        )
    }

    private fun cambiarNuevaContrasena(nuevaContrasena: String) {
        _uiState.value = _uiState.value.copy(
            nuevaContrasena = nuevaContrasena,
            mensajeError = null,
        )
    }

    private fun cambiarRepetirNuevaContrasena(repetirNuevaContrasena: String) {
        _uiState.value = _uiState.value.copy(
            repetirNuevaContrasena = repetirNuevaContrasena,
            mensajeError = null,
        )
    }

    private fun contrasenaValida(contrasena: String): Boolean {
        val tieneMinimoCaracteres = contrasena.length >= 3
        val tieneNumero = contrasena.any { caracter ->
            caracter.isDigit()
        }

        return tieneMinimoCaracteres && tieneNumero
    }



    private fun guardarCambios() {
        val usuario = SessionUsuario.obtenerUsuarioActual()!!

        val nuevoNombre = _uiState.value.nuevoNombre.trim()
        val nuevaContrasena = _uiState.value.nuevaContrasena.trim()
        val repetirNuevaContrasena = _uiState.value.repetirNuevaContrasena.trim()

        val nombreFinal = if (nuevoNombre.isBlank()) {
                    usuario.nombre
                } else {
                    nuevoNombre
                }

        val contrasenaFinal = if (nuevaContrasena.isBlank() && repetirNuevaContrasena.isBlank()) {
            usuario.contrasena
        } else {
            if (nuevaContrasena != repetirNuevaContrasena) {
                _uiState.value = _uiState.value.copy(
                    mensajeError = "la nueva contraseña y confirmar nueva contraseña deben ser iguales" +
                            "para poder actualizar la contraseña actual"
                )
                return
            }

            if (!contrasenaValida(nuevaContrasena)) {
                _uiState.value = _uiState.value.copy(
                    mensajeError = "la nueva contraseña debe tener mínimo 3 caracteres y al menos un número"
                )
                return
            }

            nuevaContrasena
        }

        val usuarioActualizado = Usuario(
            id = usuario.id,
            nombre = nombreFinal,
            email = usuario.email,
            contrasena = contrasenaFinal
        )

        UsuariosFake.actualizarUsuario(usuarioActualizado)
        SessionUsuario.iniciarSesion(usuarioActualizado)

        _uiState.value = _uiState.value.copy(
            nombreActual = usuarioActualizado.nombre,
            emailActual = usuarioActualizado.email,
            nuevoNombre = "",
            contrasenaActual = usuarioActualizado.contrasena,
            nuevaContrasena = "",
            repetirNuevaContrasena = "",
            mensajeError = null,
            perfilActualizado = true
        )
    }


}