package com.example.reservarapp.models

import java.time.LocalDate
import java.util.Date


class Reserva {

    var id : Long = 0
    var grupo : Grupo? = null
    var cliente : Cliente? = null
    var fecha : Date = Date()
    var numComensales : Int = 0
    var disposicion : String = ""
    var comentario : String = ""
    override fun toString(): String {
        return "Reserva(id=$id, grupo=${grupo?.alias}, cliente=${cliente?.nombre}, fecha=$fecha, numComensales=$numComensales, disposicion='$disposicion', comentario='$comentario')"
    }


}