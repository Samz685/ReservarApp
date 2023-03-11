package com.example.reservarapp.models

class Grupo {

    var id: Long = 0
    var alias: String = ""
    var owner: Usuario? = null
    var admin: Usuario? = null

    override fun toString(): String {
        return "Grupo(id=$id, alias='$alias', owner=${owner?.alias ?: "null"}, admin=${admin?.alias ?: "null"})"
    }

}
