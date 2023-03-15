package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsuarioController {

    val db = Firebase.firestore

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

    fun findMyGrupos(usuario: Usuario) {
        val db = Firebase.firestore

        val grupoRef = db.collection("grupos")
        val query = grupoRef.whereIn("id", usuario.listaGrupos)

        query.get().addOnSuccessListener { result ->
            var cont = 1
            for (document in result) {
                Log.i("Grupo $cont", "Grupo: ${document.data["alias"]} || ${usuario.alias}")
                cont++
            }
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun findMyTareas(usuario: Usuario) {
        val db = Firebase.firestore

        val grupoRef = db.collection("tareas")
        val query = grupoRef.whereEqualTo("asignedTo", usuario.id)

        query.get().addOnSuccessListener { result ->
            var cont = 1
            for (document in result) {
                Log.i("Tarea $cont", "Tarea: ${document.data["alias"]} || ${usuario.alias}")
                cont++
            }
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun updateUsuarioGrupo(usuario: Usuario){

        val db = Firebase.firestore
        val usuarioRef = db.collection("usuarios").document(usuario.id)

        usuarioRef.update("listaGrupos", usuario.listaGrupos).addOnSuccessListener {
            Log.i("Firebase", "Datos actualizados correctamente")
        }
    }





}