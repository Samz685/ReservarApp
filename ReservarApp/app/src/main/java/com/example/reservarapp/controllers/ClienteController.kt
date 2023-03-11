package com.example.reservarapp.controllers

import android.app.DownloadManager.Query
import android.util.Log
import com.example.reservarapp.models.Cliente
import com.example.reservarapp.models.Reserva
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ClienteController {

    fun addCliente(cliente: Cliente): String {

        val db = Firebase.firestore
        val clienteRef = db.collection("clientes").document()
        cliente.id = clienteRef.id

        val datos = hashMapOf(
            "id" to cliente.id,
            "nombre" to cliente.nombre,
            "telefono" to cliente.telefono,
            "email" to cliente.email
        )
        clienteRef.set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }

        return cliente.id
    }

    fun getById(cl: Cliente): Cliente {

        val db = Firebase.firestore
        val clienteRef = db.collection("clientes")
        var cliente = Cliente()

        var query = clienteRef.whereEqualTo("cliente.id", cl.id)

        query.get().addOnSuccessListener { result ->
            for (document in result) {
                cliente = document.toObject<Cliente>()
            }

        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }

        return cliente
    }




}
















