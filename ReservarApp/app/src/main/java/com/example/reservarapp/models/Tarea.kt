package com.example.reservarapp.models

import java.util.Date

class Tarea {

    private var id : Long = 0
    private var alias : String = ""
    private var creador : Usuario? = null
    private var asignadoA : Usuario? = null
    private var fechaCreado : Date = Date()
    private var fechaRealizado : Date = Date()
    private var grupo : Grupo? = null
    private var comentario : String = ""

    override fun toString(): String {
        return "Tarea(id=$id, alias='$alias', creador=$creador, asignadoA=${asignadoA?.alias}, fechaCreado=$fechaCreado, fechaRealizado=$fechaRealizado, grupo=${grupo?.alias}, comentario='$comentario')"
    }


}