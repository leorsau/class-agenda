package com.lehi.calendario.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.EventosFake
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.TareasFake
import com.lehi.calendario.presentation.models.UserProfileUIEvent
import com.lehi.calendario.presentation.models.UserProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserProfileViewModel: ViewModel() {
    private val usuarioActual= SessionUsuario.obtenerUsuarioActual()
    private val _uiState= MutableStateFlow(
        UserProfileUIState(
            nombre = usuarioActual?.nombre ?:"",
            email=usuarioActual?.email ?:"",
            tareasCompletadas = if(usuarioActual!=null){
                TareasFake.contarTareasCompletadasPorUsuario(usuarioActual)
            }else{
                0
            },
            tareasPendientes = if(usuarioActual!=null){
                TareasFake.contarTareasPendientesPorUsuario(usuarioActual)
            }else{
                0
            },
            eventosProximos = if(usuarioActual!=null){
                EventosFake.contarEventosProximosPorUsuario(usuarioActual)
            }else{
                0
            },
            sesionCerrada = false
        )
    )

    val uiState: StateFlow<UserProfileUIState> = _uiState

    fun onEvent(event: UserProfileUIEvent){
        when(event){
            is UserProfileUIEvent.CerrarSesionClick -> cerrarSesion()
        }
    }

    private fun cerrarSesion(){
        SessionUsuario.cerrarSesion()

        _uiState.value = _uiState.value.copy(
            sesionCerrada = true
        )
    }

}