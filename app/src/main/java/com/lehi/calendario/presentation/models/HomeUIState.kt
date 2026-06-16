package com.lehi.calendario.presentation.models

data class HomeUIState(
    val items: List<HomeItem>,
    val filtroSeleccionado: FiltroHome,
    val mostrarPopupCrear: Boolean,
    val mensajeError: String?
)
