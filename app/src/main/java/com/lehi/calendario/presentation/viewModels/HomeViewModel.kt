package com.lehi.calendario.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.EventosFake
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.TareasFake
import com.lehi.calendario.domain.model.Evento
import com.lehi.calendario.domain.model.Tarea
import com.lehi.calendario.presentation.models.FiltroHome
import com.lehi.calendario.presentation.models.HomeItem
import com.lehi.calendario.presentation.models.HomeUIEvent
import com.lehi.calendario.presentation.models.HomeUIState
import com.lehi.calendario.presentation.models.TipoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val usuarioActual = SessionUsuario.obtenerUsuarioActual()!!

    private val _uiState = MutableStateFlow(
        HomeUIState(
            items = emptyList(),
            filtroSeleccionado = FiltroHome.TODOS,
            mostrarPopupCrear = false,
            mensajeError = null
        )
    )
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        cargarDatos()
    }

    fun onEvent(event: HomeUIEvent) {
        when (event) {
            is HomeUIEvent.FiltroTodosClick -> cambiarFiltro(FiltroHome.TODOS)
            is HomeUIEvent.FiltroTareasClick -> cambiarFiltro(FiltroHome.TAREAS)
            is HomeUIEvent.FiltroEventosClick -> cambiarFiltro(FiltroHome.EVENTOS)

            is HomeUIEvent.AbrirPopupCrearClick -> abrirPopupCrear()
            is HomeUIEvent.CerrarPopupCrearClick -> cerrarPopupCrear()

            is HomeUIEvent.CrearTareaClick -> crearTarea()
            is HomeUIEvent.CrearEventoClick -> crearEvento()

        }
    }

    private fun convertirTareaAHomeItem(tarea: Tarea): HomeItem {
        return HomeItem(
            id = tarea.id,
            titulo = tarea.titulo,
            descripcion = tarea.descripcion,
            fechaInicio = tarea.fechaTarea.toString(),
            fechaFin = null,
            horaInicio = null,
            horaFin = null,
            tipo = TipoItem.TAREA,
            fechaOrden = tarea.fechaTarea.atStartOfDay()
        )
    }

    private fun convertirEventoAHomeItem(evento: Evento): HomeItem {
        return HomeItem(
            id = evento.id,
            titulo = evento.titulo,
            descripcion = evento.descripcion,
            fechaInicio = evento.fechaInicioEvento.toLocalDate().toString(),
            fechaFin = evento.fechaFinEvento.toLocalDate().toString(),
            horaInicio = evento.fechaInicioEvento.toLocalTime().toString(),
            horaFin = evento.fechaFinEvento.toLocalTime().toString(),
            tipo = TipoItem.EVENTO,
            fechaOrden = evento.fechaInicioEvento
        )
    }

    private fun filtrarItems(
        items: List<HomeItem>,
        filtro: FiltroHome
    ): List<HomeItem> {
        val itemsFiltrados = mutableListOf<HomeItem>()

        for (item in items) {
            if (filtro == FiltroHome.TODOS) {
                itemsFiltrados.add(item)
            } else if (filtro == FiltroHome.TAREAS && item.tipo == TipoItem.TAREA) {
                itemsFiltrados.add(item)
            } else if (filtro == FiltroHome.EVENTOS && item.tipo == TipoItem.EVENTO) {
                itemsFiltrados.add(item)
            }
        }

        val itemsOrdenados= itemsFiltrados.sortedBy { item ->
            item.fechaOrden
        }

        return itemsOrdenados
    }

    fun cargarDatos() {
        val tareas = TareasFake.obtenerTareasPorUsuario(usuarioActual)
        val eventos = EventosFake.obtenerEventosPorUsuario(usuarioActual)

        val items = mutableListOf<HomeItem>()

        for (tarea in tareas) {
            items.add(convertirTareaAHomeItem(tarea))
        }

        for (evento in eventos) {
            items.add(convertirEventoAHomeItem(evento))
        }

        val itemsFiltrados = filtrarItems(
            items = items,
            filtro = _uiState.value.filtroSeleccionado
        )

        _uiState.value = _uiState.value.copy(
            items = itemsFiltrados,
            mensajeError = null
        )
    }

    private fun cambiarFiltro(filtro: FiltroHome) {
        _uiState.value = _uiState.value.copy(
            filtroSeleccionado = filtro
        )

        cargarDatos()
    }

    private fun abrirPopupCrear() {
        _uiState.value = _uiState.value.copy(
            mostrarPopupCrear = true
        )
    }

    private fun cerrarPopupCrear() {
        _uiState.value = _uiState.value.copy(
            mostrarPopupCrear = false
        )
    }

    private fun crearTarea() {
        _uiState.value = _uiState.value.copy(
            mostrarPopupCrear = false
        )
    }

    private fun crearEvento() {
        _uiState.value = _uiState.value.copy(
            mostrarPopupCrear = false
        )
    }
}