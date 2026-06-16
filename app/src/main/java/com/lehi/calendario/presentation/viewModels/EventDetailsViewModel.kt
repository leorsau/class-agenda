package com.lehi.calendario.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.lehi.calendario.data.fakeDatabase.EventosFake
import com.lehi.calendario.data.fakeDatabase.SessionUsuario
import com.lehi.calendario.domain.model.Evento
import com.lehi.calendario.presentation.models.EventDetailsUIEvent
import com.lehi.calendario.presentation.models.EventDetailsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class EventDetailsViewModel: ViewModel() {
    private val usuarioActual = SessionUsuario.obtenerUsuarioActual()!!

    private val _uiState = MutableStateFlow(
        EventDetailsUIState(
            id = null,
            titulo = "",
            descripcion = "",
            fechaInicioEvento = LocalDate.now(),
            horaInicioEvento = LocalTime.of(23, 0),
            fechaFinEvento = LocalDate.now(),
            horaFinEvento = LocalTime.of(23, 45),
            modoEdicion = true,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null,
            eventoBorrado = false
        )
    )

    val uiState: StateFlow<EventDetailsUIState> = _uiState
    val horasDisponibles: List<LocalTime> = generarHorasDisponibles()

    private fun generarHorasDisponibles(): List<LocalTime> {
        val horas = mutableListOf<LocalTime>()

        for (hora in 0..23) {
            horas.add(LocalTime.of(hora, 0))
            horas.add(LocalTime.of(hora, 15))
            horas.add(LocalTime.of(hora, 30))
            horas.add(LocalTime.of(hora, 45))
        }

        return horas
    }

    fun cargarEvento(idEvento: Int) {
        val evento = EventosFake.obtenerEventoPorId(idEvento)

        if (evento != null) {
            _uiState.value = _uiState.value.copy(
                id = evento.id,
                titulo = evento.titulo,
                descripcion = evento.descripcion,
                fechaInicioEvento = evento.fechaInicioEvento.toLocalDate(),
                horaInicioEvento = evento.fechaInicioEvento.toLocalTime(),
                fechaFinEvento = evento.fechaFinEvento.toLocalDate(),
                horaFinEvento = evento.fechaFinEvento.toLocalTime(),
                modoEdicion = false,
                mensajeErrorTitulo = null,
                mensajeErrorFechaFin = null,
                eventoBorrado = false
            )
        } else {
            _uiState.value = _uiState.value.copy(
                mensajeErrorTitulo = "No se ha encontrado el evento"
            )
        }
    }

    fun onEvent(event: EventDetailsUIEvent) {
        when (event) {
            is EventDetailsUIEvent.TituloCambiado -> cambiarTitulo(event.nuevoTitulo)
            is EventDetailsUIEvent.DescripcionCambiada -> cambiarDescripcion(event.nuevaDescripcion)
            is EventDetailsUIEvent.FechaInicioCambiada -> cambiarFechaInicio(event.nuevaFechaInicio)
            is EventDetailsUIEvent.HoraInicioCambiada -> cambiarHoraInicio(event.nuevaHoraInicio)
            is EventDetailsUIEvent.FechaFinCambiada -> cambiarFechaFin(event.nuevaFechaFin)
            is EventDetailsUIEvent.HoraFinCambiada -> cambiarHoraFin(event.nuevaHoraFin)

            is EventDetailsUIEvent.EditarClick -> activarModoEdicion()
            is EventDetailsUIEvent.GuardarClick -> guardarCambios()
            is EventDetailsUIEvent.BorrarClick -> borrarEvento()

        }
    }

    private fun cambiarTitulo(nuevoTitulo: String) {
        _uiState.value = _uiState.value.copy(
            titulo = nuevoTitulo,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun cambiarDescripcion(nuevaDescripcion: String) {
        _uiState.value = _uiState.value.copy(
            descripcion = nuevaDescripcion,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun cambiarFechaInicio(nuevaFechaInicio: LocalDate) {
        _uiState.value = _uiState.value.copy(
            fechaInicioEvento = nuevaFechaInicio,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun cambiarHoraInicio(nuevaHoraInicio: LocalTime) {
        _uiState.value = _uiState.value.copy(
            horaInicioEvento = nuevaHoraInicio,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun cambiarFechaFin(nuevaFechaFin: LocalDate) {
        _uiState.value = _uiState.value.copy(
            fechaFinEvento = nuevaFechaFin,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun cambiarHoraFin(nuevaHoraFin: LocalTime) {
        _uiState.value = _uiState.value.copy(
            horaFinEvento = nuevaHoraFin,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun activarModoEdicion() {
        _uiState.value = _uiState.value.copy(
            modoEdicion = true,
            mensajeErrorTitulo = null,
            mensajeErrorFechaFin = null
        )
    }

    private fun guardarCambios() {
        val estado = _uiState.value

        if (estado.titulo.isBlank()) {
            _uiState.value = estado.copy(
                mensajeErrorTitulo = "El título no puede estar vacío",
                mensajeErrorFechaFin = null
            )
            return
        }

        val fechaInicioCompleta = LocalDateTime.of(
            estado.fechaInicioEvento,
            estado.horaInicioEvento
        )

        val fechaFinCompleta = LocalDateTime.of(
            estado.fechaFinEvento,
            estado.horaFinEvento
        )

        if (fechaFinCompleta.isBefore(fechaInicioCompleta)) {
            _uiState.value = estado.copy(
                mensajeErrorTitulo = null,
                mensajeErrorFechaFin = "La fecha de fin no puede ser anterior a la fecha de inicio"
            )
            return
        }

        if (estado.id == null) {
            val nuevoEvento = EventosFake.crearEvento(
                usuario = usuarioActual,
                titulo = estado.titulo.trim(),
                descripcion = estado.descripcion,
                fechaInicioEvento = fechaInicioCompleta,
                fechaFinEvento = fechaFinCompleta
            )

            _uiState.value = estado.copy(
                id = nuevoEvento.id,
                modoEdicion = false,
                mensajeErrorTitulo = null,
                mensajeErrorFechaFin = null
            )
        } else {
            EventosFake.actualizarEvento(
                Evento(
                    id = estado.id,
                    usuario = usuarioActual,
                    titulo = estado.titulo.trim(),
                    descripcion = estado.descripcion,
                    fechaInicioEvento = fechaInicioCompleta,
                    fechaFinEvento = fechaFinCompleta
                )
            )

            _uiState.value = estado.copy(
                modoEdicion = false,
                mensajeErrorTitulo = null,
                mensajeErrorFechaFin = null
            )
        }
    }

    private fun borrarEvento() {
        val estado = _uiState.value

        if (estado.id != null) {
            EventosFake.borrarEvento(estado.id)
            _uiState.value = estado.copy(
                eventoBorrado = true
            )
        }
    }




}