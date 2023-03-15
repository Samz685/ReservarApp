package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Reserva
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UsuarioController {

    fun addUsuario(usuario: Usuario) : String{

        val db = Firebase.firestore
        val usuarioRef = db.collection("usuarios").document()

        usuario.id = usuarioRef.id

        val datos = hashMapOf(
            "id" to usuario.id,
            "alias" to usuario.alias,
            "email" to usuario.email,
            "password" to usuario.password,
            "listaGrupos" to usuario.listaGrupos,
            "grupoActual" to usuario.grupoActual
        )
        usuarioRef.set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }

        return usuario.id
    }


}