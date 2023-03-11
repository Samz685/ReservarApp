package com.example.reservarapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.reservarapp.models.Cliente
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Reserva
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var listaReservas = mutableListOf<Reserva>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaReservas = mutableListOf<Reserva>()



        var us1: Usuario = Usuario()
        us1.id = 1
        us1.alias = "Sam"
        us1.email = "sam@email.com"

        var gp1: Grupo = Grupo()
        gp1.id = 1
        gp1.alias = "El Pikon"
        gp1.owner = us1

        var cl: Cliente = Cliente()
        cl.id = 1
        cl.nombre = "Pepito"
        cl.email = "pepito@email.com"
        cl.telefono = "622622622"

        var cl2: Cliente = Cliente()
        cl2.id = 2
        cl2.nombre = "Juanito"
        cl2.email = "juanito@email.com"
        cl2.telefono = "633633633"


        var re: Reserva = Reserva()
        re.id = 1
        re.cliente = cl
        re.numComensales = 4
        re.disposicion = "Terraza"
        re.comentario = "Quieren una botella de champán"
        re.grupo = gp1

        var re2: Reserva = Reserva()
        re2.id = 2
        re2.cliente = cl2
        re2.numComensales = 6
        re2.disposicion = "Interior"
        re2.comentario = "Celebracion de cumpleaños"
        re2.grupo = gp1


        println("El grupo-------------------")
        println(gp1)

//        insertarUsuario(us1)
//        insertarGrupo(gp1)
//        insertarReserva(re)
//        insertarReserva(re2)

        leerDatos()
    }

    private fun insertarUsuario(usuario: Usuario) {
        val db = Firebase.firestore
        val datos = hashMapOf(
            "alias" to usuario.alias,
            "email" to usuario.email,
            "listaGrupos" to usuario.listaGrupos,
            "grupoActual" to usuario.grupoActual
        )
        db.collection("usuarios").document(usuario.alias).set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

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

    private fun insertarReserva(reserva: Reserva) {
        val db = Firebase.firestore
        val datos = hashMapOf(
            "cliente" to reserva.cliente,
            "fecha" to reserva.fecha,
            "numComensales" to reserva.numComensales,
            "disposicion" to reserva.disposicion,
            "comentario" to reserva.comentario,
            "grupo" to reserva.grupo
        )
        db.collection("reservas").document(reserva.id.toString()).set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

//    private fun reservarCliente(cliente: Cliente){
//
//        val db = Firebase.firestore
//        val ref = db.collection("usuarios")
//        ref.whereEqualTo("cliente", cliente).get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    val reserva = document.toObject(Reserva::class.java)
//                    listaReservas.add(reserva)
//                    println("----------------------00000000000000000000")
//                    println(reserva.toString())
//                }
//            }
//            .addOnFailureListener { exception ->
//
//                Log.w(TAG, "Error getting documents: ", exception)
//            }
//
//
//    }

    fun leerDatos() {
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




}