package com.lehi.calendario.presentation.viewModels


import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.UsuariosFake
import com.lehi.calendario.presentation.models.LoginUIEvent
import com.lehi.calendario.presentation.models.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {

    private val _uiState= MutableStateFlow(
        LoginUIState(
            email = "",
            contrasena = "",
            mensajeErrorCamposVacios = null,
            mensajeErrorEmailContrasena = null,
            loginCorrecto = false
        )
    )

    val uiState: StateFlow<LoginUIState> = _uiState

    fun onEvent(event: LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailCambiado ->cambiarEmail(event.nuevoEmail)
            is LoginUIEvent.ContrasenaCambiada -> cambiarContrasena(event.nuevaContrasena)
            is LoginUIEvent.EntrarClick -> validarLogin()
        }
    }

    private fun cambiarEmail(nuevoEmail: String){
        _uiState.value= _uiState.value.copy(
            email = nuevoEmail,
            mensajeErrorCamposVacios = null,
            mensajeErrorEmailContrasena = null,
            loginCorrecto = false
        )
    }

    private fun cambiarContrasena(nuevaContrasena:String){
        _uiState.value=_uiState.value.copy(
            contrasena = nuevaContrasena,
            mensajeErrorCamposVacios = null,
            mensajeErrorEmailContrasena = null,
            loginCorrecto = false
        )
    }

    private fun validarLogin(){
        val email=_uiState.value.email.trim()
        val contrasena=_uiState.value.contrasena.trim()
        if(email.isBlank()|| contrasena.isBlank()){
            _uiState.value=_uiState.value.copy(
                mensajeErrorCamposVacios = "debes rellenar todos los campos!!",
                mensajeErrorEmailContrasena = null,
                loginCorrecto = false
            )
        }else{
            val usuarioEncontrado= UsuariosFake.login(email,contrasena)
            if(usuarioEncontrado!=null){
                SessionUsuario.iniciarSesion(usuarioEncontrado)
                _uiState.value=_uiState.value.copy(
                    mensajeErrorCamposVacios = null,
                    mensajeErrorEmailContrasena = null,
                    loginCorrecto = true
                )
            }else{
                _uiState.value = _uiState.value.copy(
                    mensajeErrorCamposVacios = null,
                    mensajeErrorEmailContrasena = "email o contraseña incorrectos",
                    loginCorrecto = false
                )
            }

        }
    }


}