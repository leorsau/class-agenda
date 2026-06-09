package com.lehi.calendario.presentation.viewModels


import androidx.lifecycle.ViewModel
import com.lehi.calendario.presentation.models.LoginUIEvent
import com.lehi.calendario.presentation.models.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {

    private val _uiState= MutableStateFlow(
        LoginUIState(
            email = "",
            contrasena = "",
            mensajeError = null,
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
            mensajeError = null
        )
    }

    private fun cambiarContrasena(nuevaContrasena:String){
        _uiState.value=_uiState.value.copy(
            contrasena = nuevaContrasena,
            mensajeError = null
        )
    }

    private fun validarLogin(){
        val email=_uiState.value.email
        val contrasena=_uiState.value.contrasena
        if(email.isBlank()|| contrasena.isBlank()){
            _uiState.value=_uiState.value.copy(
                mensajeError = "debes rellenar todos los campos!!",
                loginCorrecto = false
            )
        }else{
            _uiState.value=_uiState.value.copy(
                mensajeError = null,
                loginCorrecto = true
            )
        }
    }


}