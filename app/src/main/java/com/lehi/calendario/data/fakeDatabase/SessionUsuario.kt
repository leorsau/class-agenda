package com.lehi.calendario.data.fakeDatabase

import com.lehi.calendario.domain.model.Usuario

object SessionUsuario {
    private var usuarioActual: Usuario?=null

    fun iniciarSesion(usuario: Usuario){
        usuarioActual=usuario
    }

    fun cerrarSesion(){
        usuarioActual=null
    }

    fun obtenerUsuarioActual(): Usuario?{
        return usuarioActual
    }
}