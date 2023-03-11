package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Reserva
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UsuarioController {

//    fun getKey(): Long {
//        var id: Long = 0
//        val db = Firebase.firestore
//        val usuarioRef = db.collection("usuarios")
//        val q = usuarioRef.orderBy("usuarios.id", Query.Direction.DESCENDING).limit(1)
//
//        q.get().addOnSuccessListener { result ->
//            for (document in result) {
//                id = document.toObject<Usuario>().id
//                id += 1
//            }
//
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.message.toString())
//            id = 0
//        }
//        return id
//    }

//    fun addUsuario(usuario: Usuario) {
//        val db = Firebase.firestore
//        val datos = hashMapOf(
//            "id" to getKey(),
//            "alias" to usuario.alias,
//            "email" to usuario.email,
//            "listaGrupos" to usuario.listaGrupos,
//            "grupoActual" to usuario.grupoActual
//        )
//        db.collection("usuarios").add(datos).addOnSuccessListener {
//            Log.i("Firebase", "Datos insertados correctamente")
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.message.toString())
//        }
//    }

    fun addUsuario(usuario: Usuario) : String{

        val db = Firebase.firestore
        val usuarioRef = db.collection("usuarios").document()

        usuario.id = usuarioRef.id

        val datos = hashMapOf(
            "id" to usuario.id,
            "alias" to usuario.alias,
            "email" to usuario.email,
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