package com.lehi.calendario.presentation.models

sealed interface UserProfileUIEvent {
    data object CerrarSesionClick: UserProfileUIEvent
}