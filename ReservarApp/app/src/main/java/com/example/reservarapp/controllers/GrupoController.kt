package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Grupo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GrupoController {

    private fun insertarGrupo(grupo: Grupo) {
        val db = Firebase.firestore
        val datos = hashMapOf(
            "alias" to grupo.alias,
            "owner" to grupo.owner
        )
        db.collection("grupos").document(grupo.alias).set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }
}