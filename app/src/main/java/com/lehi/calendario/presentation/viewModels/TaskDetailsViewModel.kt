package com.lehi.calendario.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.data.fakeDatabase.TareasFake
import com.lehi.calendario.domain.model.EstadoTarea
import com.lehi.calendario.domain.model.PrioridadTarea
import com.lehi.calendario.domain.model.Tarea
import com.lehi.calendario.presentation.models.TaskDetailsUIEvent
import com.lehi.calendario.presentation.models.TaskDetailsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class TaskDetailsViewModel: ViewModel() {

    private val usuarioActual= SessionUsuario.obtenerUsuarioActual()!!
    private val _uiState = MutableStateFlow(
        TaskDetailsUIState(
            id = null,
            titulo = "",
            descripcion = "",
            fechaTarea = LocalDate.now(),
            estadoTarea = EstadoTarea.PENDIENTE,
            prioridadTarea = PrioridadTarea.BAJA,
            modoEdicion = true,
            mensajeError = null,
            tareaBorrada = false
        )
    )

    val uiState: StateFlow<TaskDetailsUIState> = _uiState

    fun cargarTarea(idTarea: Int) {
        val tarea = TareasFake.obtenerTareaPorId(idTarea)

        if (tarea != null) {
            _uiState.value = _uiState.value.copy(
                id = tarea.id,
                titulo = tarea.titulo,
                descripcion = tarea.descripcion ?: "",
                fechaTarea = tarea.fechaTarea,
                estadoTarea = tarea.estadoTarea,
                prioridadTarea = tarea.prioridadTarea,
                modoEdicion = false,
                mensajeError = null,
                tareaBorrada = false
            )
        } else {
            _uiState.value = _uiState.value.copy(
                mensajeError = "No se ha encontrado la tarea"
            )
        }
    }

    fun onEvent(event: TaskDetailsUIEvent) {
        when (event) {
            is TaskDetailsUIEvent.TituloCambiado -> cambiarTitulo(event.nuevoTitulo)
            is TaskDetailsUIEvent.DescripcionCambiada -> cambiarDescripcion(event.nuevaDescripcion)
            is TaskDetailsUIEvent.FechaCambiada -> cambiarFecha(event.nuevaFecha)
            is TaskDetailsUIEvent.PrioridadCambiada -> cambiarPrioridad(event.nuevaPrioridad)
            is TaskDetailsUIEvent.EstadoCambiado -> cambiarEstado(event.nuevoEstado)

            is TaskDetailsUIEvent.EditarClick -> activarModoEdicion()
            is TaskDetailsUIEvent.GuardarClick -> guardarCambios()
            is TaskDetailsUIEvent.BorrarClick -> borrarTarea()

        }
    }

    private fun cambiarTitulo(nuevoTitulo: String) {
        _uiState.value = _uiState.value.copy(
            titulo = nuevoTitulo,
            mensajeError = null
        )
    }

    private fun cambiarDescripcion(nuevaDescripcion: String) {
        _uiState.value = _uiState.value.copy(
            descripcion = nuevaDescripcion,
            mensajeError = null
        )
    }

    private fun cambiarFecha(nuevaFecha: LocalDate) {
        _uiState.value = _uiState.value.copy(
            fechaTarea = nuevaFecha,
            mensajeError = null
        )
    }

    private fun cambiarPrioridad(nuevaPrioridad: PrioridadTarea) {
        _uiState.value = _uiState.value.copy(
            prioridadTarea = nuevaPrioridad,
            mensajeError = null
        )
    }

    private fun cambiarEstado(nuevoEstado: EstadoTarea) {
        _uiState.value = _uiState.value.copy(
            estadoTarea = nuevoEstado,
            mensajeError = null
        )
    }

    private fun activarModoEdicion() {
        _uiState.value = _uiState.value.copy(
            modoEdicion = true,
            mensajeError = null
        )
    }

    private fun guardarCambios(){
       val estado=_uiState.value

        if (estado.titulo.isBlank()) {
            _uiState.value = estado.copy(
                mensajeError = "El título no puede estar vacío"
            )
            return
        }

        if(estado.id==null){
            val nuevaTarea= TareasFake.crearTarea(
                usuario = usuarioActual,
                titulo = estado.titulo.trim(),
                descripcion = estado.descripcion,
                fechaTarea = estado.fechaTarea,
                estadoTarea = estado.estadoTarea,
                prioridadTarea = estado.prioridadTarea
            )

            _uiState.value=_uiState.value.copy(
                id=nuevaTarea.id,
                modoEdicion = false,
                mensajeError = null
            )
        }else{
            TareasFake.actualizarTarea(
                Tarea(
                    id=estado.id,
                    usuario = usuarioActual,
                    titulo = estado.titulo,
                    descripcion = estado.descripcion,
                    fechaTarea = estado.fechaTarea,
                    estadoTarea = estado.estadoTarea,
                    prioridadTarea = estado.prioridadTarea
                )
            )

            _uiState.value=_uiState.value.copy(
                modoEdicion = false,
                mensajeError = null
            )
        }
    }

    private fun borrarTarea() {
        val estado = _uiState.value

        if (estado.id != null) {
            TareasFake.borrarTarea(estado.id)
            _uiState.value = estado.copy(tareaBorrada = true)
        }
    }




}