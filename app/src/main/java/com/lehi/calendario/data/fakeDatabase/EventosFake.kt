package com.lehi.calendario.data.fakeDatabase

import com.lehi.calendario.domain.model.Evento
import com.lehi.calendario.domain.model.Usuario
import java.time.LocalDate
import java.time.LocalDateTime

object EventosFake {
    private val eventos=mutableListOf<Evento>()

    fun crearEvento(
        usuario: Usuario,
        titulo: String,
        descripcion: String,
        fechaInicioEvento: LocalDateTime,
        fechaFinEvento: LocalDateTime
    ): Evento {
        val nuevoEvento = Evento(
            id = obtenerNuevoId(),
            usuario = usuario,
            titulo = titulo,
            descripcion = descripcion,
            fechaInicioEvento = fechaInicioEvento,
            fechaFinEvento = fechaFinEvento
        )

        eventos.add(nuevoEvento)
        return nuevoEvento
    }

    private fun obtenerNuevoId(): Int {
        val ultimoEvento = eventos.lastOrNull()
        val ultimoId: Int = ultimoEvento?.id ?: 0
        return ultimoId + 1
    }

    fun obtenerEventoPorId(id: Int): Evento? {
        for (evento in eventos) {
            if (evento.id == id) {
                return evento
            }
        }

        return null
    }

    fun obtenerEventosPorUsuario(usuario: Usuario): List<Evento> {
        val eventosUsuario = mutableListOf<Evento>()
        val fechaHoraActual = LocalDateTime.now()

        for (evento in eventos) {
            if (
                evento.usuario.id == usuario.id &&
                (
                        evento.fechaFinEvento.isAfter(fechaHoraActual) ||
                                evento.fechaFinEvento.equals(fechaHoraActual)
                        )
            ) {
                eventosUsuario.add(evento)
            }
        }

        return eventosUsuario
    }

    fun actualizarEvento(eventoActualizado: Evento) {
        for (indice in eventos.indices) {
            if (eventos[indice].id == eventoActualizado.id) {
                eventos[indice] = eventoActualizado
                return
            }
        }
    }

    fun borrarEvento(id: Int) {
        eventos.removeIf { evento ->
            evento.id == id
        }
    }

    fun contarEventosProximosPorUsuario(usuario: Usuario): Int {
        var contador = 0

        val eventosUsuario = obtenerEventosPorUsuario(usuario)

        for (evento in eventosUsuario) {
            contador++
        }

        return contador
    }
}