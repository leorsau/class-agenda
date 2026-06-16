package com.lehi.calendario.presentation.viewModels


import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.UsuariosFake
import com.lehi.calendario.presentation.models.RegisterUIEvent
import com.lehi.calendario.presentation.models.RegisterUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel: ViewModel() {
    private val _uiState= MutableStateFlow(
        RegisterUIState(
            nombre = "",
            email = "",
            contrasena = "",
            repetirContrasena = "",
            mensajeErrorCampos = null,
            mensajeErrorContrasena = null,
            mensajeErrorEmailFormat = null,
            registroCorrecto = false
        )
    )

    val uiState: StateFlow<RegisterUIState> =_uiState

    fun onEvent(event: RegisterUIEvent){
        when(event){
            is RegisterUIEvent.NombreCambiado -> cambiarNombre(event.nuevoNombre)
            is RegisterUIEvent.EmailCambiado -> cambiarEmail(event.nuevoEmail)
            is RegisterUIEvent.ContrasenaCambiada -> cambiarContrasena(event.nuevaContrasena)
            is RegisterUIEvent.RepetirContrasenaCambiada -> cambiarRepetirContrasena(event.nuevaRepetirContrasena)
            is RegisterUIEvent.CrearCuentaClick -> validarRegistro()
        }
    }


    private fun cambiarNombre(nuevoNombre: String) {
        _uiState.value = _uiState.value.copy(
            nombre = nuevoNombre,
            mensajeErrorCampos = null,
            mensajeErrorContrasena = null,
            mensajeErrorEmailFormat = null,
            registroCorrecto = false
        )
    }

    private fun cambiarEmail(nuevoEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = nuevoEmail,
            mensajeErrorCampos = null,
            mensajeErrorContrasena = null,
            mensajeErrorEmailFormat = null,
            registroCorrecto = false
        )
    }

    private fun cambiarContrasena(nuevaContrasena: String) {
        _uiState.value = _uiState.value.copy(
            contrasena = nuevaContrasena,
            mensajeErrorCampos = null,
            mensajeErrorContrasena = null,
            mensajeErrorEmailFormat = null,
            registroCorrecto = false
        )
    }

    private fun cambiarRepetirContrasena(nuevaRepetirContrasena: String) {
        _uiState.value = _uiState.value.copy(
            repetirContrasena = nuevaRepetirContrasena,
            mensajeErrorCampos = null,
            mensajeErrorContrasena = null,
            mensajeErrorEmailFormat = null,
            registroCorrecto = false
        )
    }

    private fun emailValido(email: String): Boolean{
        return email.contains("@") &&
                email.contains(".")&&
                email.count{caracter->caracter =='@'}==1
    }

    private fun contrasenaValida(contrasena:String): Boolean{
        val tieneMinimoCaracteres=contrasena.length>=3
        val tieneNumero=contrasena.any{
            caracter-> caracter.isDigit()
        }
        return  tieneMinimoCaracteres&&tieneNumero
    }

    private fun validarRegistro(){
        val nombre=_uiState.value.nombre.trim()
        val email=_uiState.value.email.trim()
        val contrasena=_uiState.value.contrasena.trim()
        val repetirContrasena=_uiState.value.repetirContrasena.trim()

        if(
            nombre.isBlank()||
            email.isBlank()||
            contrasena.isBlank()||
            repetirContrasena.isBlank()
        ){
            _uiState.value=_uiState.value.copy(
                mensajeErrorCampos = "debes rellenar todos los campos",
                mensajeErrorContrasena = null,
                mensajeErrorEmailFormat = null,
                registroCorrecto = false
            )
        }else if(!emailValido(email)){
            _uiState.value=_uiState.value.copy(
                mensajeErrorCampos = null,
                mensajeErrorContrasena = null,
                mensajeErrorEmailFormat = "el formato del email es incorrecto",
                registroCorrecto = false
            )
        }else if(!contrasenaValida(contrasena)){
            _uiState.value = _uiState.value.copy(
                mensajeErrorCampos = null,
                mensajeErrorContrasena = "La contraseña debe tener mínimo 3 caracteres y al menos un número",
                mensajeErrorEmailFormat = null,
                registroCorrecto = false
            )
        } else if(contrasena!=repetirContrasena){
            _uiState.value=_uiState.value.copy(
                mensajeErrorCampos = null,
                mensajeErrorContrasena = "las contraseñas no coinciden",
                mensajeErrorEmailFormat = null,
                registroCorrecto = false
            )
        }else{
              val usuarioRegistrado=  UsuariosFake.registrarUsuario(
                    nombre=nombre,
                    email=email,
                    contrasena=contrasena
                )

                SessionUsuario.iniciarSesion(usuarioRegistrado)

                _uiState.value=_uiState.value.copy(
                    mensajeErrorCampos = null,
                    mensajeErrorContrasena = null,
                    mensajeErrorEmailFormat = null,
                    registroCorrecto = true
                )
        }
    }

}