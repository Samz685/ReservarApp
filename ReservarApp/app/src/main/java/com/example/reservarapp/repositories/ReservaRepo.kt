package com.example.reservarapp.repositories

import android.util.Log
import com.example.reservarapp.models.Cliente
import com.example.reservarapp.models.Reserva
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReservaRepo {

        fun getAll() {
        var reserva = Reserva()
        val db = Firebase.firestore
        db.collection("reservas").get().addOnSuccessListener { result ->
            for (document in result) {
                reserva = document.toObject<Reserva>()
                println("0000000000000-----------------------------0000000000000000000")
                println(reserva.toString())
            }

        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun getByCliente(cl : Cliente) {
        var reserva = Reserva()
        val db = Firebase.firestore

        val reservasDb = db.collection("reservas")
        val query = reservasDb.whereEqualTo("cliente.nombre", cl.alias)

        query.get().addOnSuccessListener { result ->
            for (document in result) {
                reserva = document.toObject<Reserva>()
                println("0000000000000-----------------------------0000000000000000000")
                println(reserva.toString())
            }

        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun addReserva(reserva: Reserva) : String{
        val db = Firebase.firestore
        val reservaRef = db.collection("reservas").document()
        reserva.id = reservaRef.id

        val datos = hashMapOf(
            "id" to reserva.id,
            "grupo" to reserva.grupo,
            "alias" to reserva.alias,
            "cliente" to reserva.cliente,
            "fecha" to reserva.fecha,
            "numComensales" to reserva.numComensales,
            "disposicion" to reserva.disposicion,
            "comentario" to reserva.comentario,

        )
        reservaRef.set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
        return reserva.id
    }

}