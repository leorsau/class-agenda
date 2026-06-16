package com.lehi.calendario.data.fakeDatabase

import com.lehi.calendario.domain.model.EstadoTarea
import com.lehi.calendario.domain.model.PrioridadTarea
import com.lehi.calendario.domain.model.Tarea
import com.lehi.calendario.domain.model.Usuario
import java.time.LocalDate

object TareasFake {

    private val tareas=mutableListOf<Tarea>()

    fun crearTarea(
        usuario: Usuario,
        titulo: String,
        descripcion: String?,
        fechaTarea: LocalDate,
        estadoTarea: EstadoTarea,
        prioridadTarea: PrioridadTarea
    ): Tarea{
        val nuevaTarea=Tarea(
            id=obtenerNuevoId(),
            usuario=usuario,
            titulo=titulo,
            descripcion=descripcion,
            fechaTarea=fechaTarea,
            estadoTarea=estadoTarea,
            prioridadTarea=prioridadTarea
        )

        tareas.add(nuevaTarea)
        return nuevaTarea
    }

    private fun obtenerNuevoId(): Int {
        val ultimaTarea=tareas.lastOrNull()
        val ultimoId: Int =ultimaTarea?.id?:0
        return ultimoId+1
    }

    fun obtenerTareaPorId(id:Int): Tarea?{
        for(tarea in tareas){
            if(tarea.id==id){
                return tarea
            }
        }
        return null
    }

    fun obtenerTareasPorUsuario(usuario: Usuario): List<Tarea>{
        val tareasUsuario=mutableListOf<Tarea>()

        for(tarea in tareas){
            if(tarea.usuario.id==usuario.id &&
                (tarea.fechaTarea.isAfter(LocalDate.now()) ||
                tarea.fechaTarea.equals(LocalDate.now()))
                ){
                tareasUsuario.add(tarea)
            }
        }
        return tareasUsuario
    }

    fun actualizarTarea(tareaActualizada: Tarea){
        for(indice in tareas.indices){
            if(tareas[indice].id==tareaActualizada.id){
                tareas[indice]=tareaActualizada
                return
            }
        }
    }

    fun borrarTarea(id: Int) {
        tareas.removeIf { tarea ->
            tarea.id == id
        }
    }

    fun contarTareasCompletadasPorUsuario(usuario: Usuario): Int {
        var contador = 0

        val tareasUsuario = obtenerTareasPorUsuario(usuario)

        for (tarea in tareasUsuario) {
            if (tarea.estadoTarea == EstadoTarea.FINALIZADA) {
                contador++
            }
        }

        return contador
    }

    fun contarTareasPendientesPorUsuario(usuario: Usuario): Int {
        var contador = 0

        val tareasUsuario = obtenerTareasPorUsuario(usuario)

        for (tarea in tareasUsuario) {
            if (tarea.estadoTarea == EstadoTarea.PENDIENTE) {
                contador++
            }
        }

        return contador
    }

}