package com.example.reservarapp.models

class Grupo {

    var id: String = ""
    var alias: String = ""
    var owner: String? = ""
    var admin: String? = ""
    override fun toString(): String {
        return "Grupo(id=$id, alias='$alias', owner=$owner, admin=$admin)"
    }


}
