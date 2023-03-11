package com.example.reservarapp.models

import java.util.Objects

class Usuario {

    var id : Long = 0
    var alias : String = ""
    var email : String = ""
    var listaGrupos : MutableList<Grupo> = mutableListOf()
    var grupoActual : Grupo? = null

    override fun toString(): String {
        return "Usuario(id=$id, alias='$alias', email='$email', listaGrupos=$listaGrupos, grupoActual=$grupoActual)"
    }


}