package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Grupo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GrupoController {

    private fun addGrupo(grupo: Grupo) {
        val db = Firebase.firestore
        val grupoRef = db.collection("grupos").document()
        grupo.id = grupoRef.id


        val datos = hashMapOf(
            "id" to grupo.id,
            "alias" to grupo.alias,
            "owner" to grupo.owner,
            "admin" to grupo.admin
        )
        db.collection("grupos").document(grupo.alias).set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }
}