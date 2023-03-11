package com.example.reservarapp.models

class Cliente {

    var id : Long = 0
    var nombre : String = ""
    var telefono : String = ""
    var email : String = ""
    override fun toString(): String {
        return "Cliente(id=$id, nombre='$nombre', telefono='$telefono', email='$email')"
    }


}