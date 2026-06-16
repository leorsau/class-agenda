package com.lehi.calendario.presentation.models

sealed interface HomeUIEvent {
    data object FiltroTodosClick : HomeUIEvent
    data object FiltroTareasClick : HomeUIEvent
    data object FiltroEventosClick : HomeUIEvent

    data object AbrirPopupCrearClick : HomeUIEvent
    data object CerrarPopupCrearClick : HomeUIEvent

    data object CrearTareaClick : HomeUIEvent
    data object CrearEventoClick : HomeUIEvent

}