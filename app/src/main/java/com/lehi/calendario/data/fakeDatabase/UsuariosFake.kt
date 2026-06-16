package com.lehi.calendario.data.fakeDatabase

import com.lehi.calendario.domain.model.Usuario

object UsuariosFake{
    private val usuarios=mutableListOf<Usuario>()

    fun login(email: String, contrasena:String): Usuario?{
        for(usuario in usuarios){
            if(usuario.email==email && usuario.contrasena==contrasena){
                return usuario
            }
        }
        return null
    }

    fun registrarUsuario(nombre: String, email: String, contrasena: String ): Usuario{
       val nuevoUsuario= Usuario(
           id=obtenerNuevoId(),
           nombre=nombre,
           email=email,
           contrasena=contrasena
       )
        usuarios.add(nuevoUsuario)
        return nuevoUsuario
    }

    private fun obtenerNuevoId():Int{
        return usuarios.size + 1
    }

    fun actualizarUsuario(usuarioActualizado: Usuario) {
        for (indice in usuarios.indices) {
            if (usuarios[indice].id == usuarioActualizado.id) {
                usuarios[indice] = usuarioActualizado
                return
            }
        }
    }
}