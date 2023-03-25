package com.example.reservarapp.models

import java.util.Date


class Reserva {

    var id: String = ""
    var grupo: String? = ""
    var cliente: String? = ""
    var telefono: String? = ""
    var fecha: Date = Date()
    var numComensales: Int = 0
    var disposicion: String? = ""
    var comentario: String? = ""
    var owner: String? = ""


    override fun toString(): String {
        return "Reserva(id='$id', grupo=$grupo, telefono=$telefono, cliente=$cliente, fecha=$fecha, numComensales=$numComensales, disposicion=$disposicion, comentario=$comentario, owner=$owner)"
    }


}