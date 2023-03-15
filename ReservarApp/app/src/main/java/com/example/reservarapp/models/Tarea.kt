package com.example.reservarapp.models

import java.util.Date

class Tarea {

    var id : String = ""
    var alias : String = ""
    var owner : String? = ""
    var asignedTo : String? = ""
    var createdDate : Date = Date()
    var doneDate : Date = Date()
    var grupo : String? = ""
    var comentario : String = ""


    override fun toString(): String {
        return "Tarea(id='$id', alias='$alias', createdBy=$owner, asignedTo=$asignedTo, createdDate=$createdDate, doneDate=$doneDate, group=$grupo, comentario='$comentario')"
    }


}