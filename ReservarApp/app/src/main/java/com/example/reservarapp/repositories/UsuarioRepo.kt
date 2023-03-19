package com.example.reservarapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.LinkedList

class UsuarioRepo {

    private val db = Firebase.firestore

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

    fun updateUsuario(usuario: Usuario) {

        var usuarioRef = db.collection("usuarios").document(usuario.id)


        val datos = hashMapOf(
            "alias" to usuario.alias,
            "email" to usuario.email,
            "password" to usuario.password,
            "listaGrupos" to usuario.listaGrupos,
            "grupoActual" to usuario.grupoActual
        )
        usuarioRef.update(datos).addOnSuccessListener {
            Log.i("FireBaase", "Usuario Actualizado")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }
    fun getById(id : String) : LiveData<Usuario>{
        var usuarioData = MutableLiveData<Usuario>()
        val usuarioRef = db.collection("usuarios").document(id)
        usuarioRef.get().addOnSuccessListener { result->
            var usuario = result.toObject<Usuario>()!!
            usuarioData.value = usuario
        }

        return usuarioData

    }

    fun getAll(): LiveData<LinkedList<Usuario>> {
        var usuariosData = MutableLiveData<LinkedList<Usuario>>()
        val usuarioRef = db.collection("usuarios")
        usuarioRef.get().addOnSuccessListener { result ->
            var listaUsuarios = LinkedList<Usuario>()
            for (document in result) {
                var usuario = document.toObject<Usuario>()!!
                listaUsuarios.addLast(usuario)
            }
            usuariosData.value = listaUsuarios
        }
        return usuariosData
    }

    fun getByGroup(grupo : Grupo): LiveData<LinkedList<Usuario>> {
        var usuariosData = MutableLiveData<LinkedList<Usuario>>()
        val usuarioRef = db.collection("usuarios")
        val query = usuarioRef.whereArrayContains("listaGrupos", grupo.id)
        query.get().addOnSuccessListener { result ->
            var listaUsuarios = LinkedList<Usuario>()
            for (document in result) {
                var usuario = document.toObject<Usuario>()!!
                listaUsuarios.addLast(usuario)
            }
            usuariosData.value = listaUsuarios
        }
        return usuariosData
    }




//    fun findMyGrupos(usuario: Usuario) {
//        val db = Firebase.firestore
//
//        val grupoRef = db.collection("grupos")
//        val query = grupoRef.whereIn("id", usuario.listaGrupos)
//
//        query.get().addOnSuccessListener { result ->
//            var cont = 1
//            for (document in result) {
//                Log.i("Grupo $cont", "Grupo: ${document.data["alias"]} || ${usuario.alias}")
//                cont++
//            }
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.message.toString())
//        }
//    }
//
//    fun findMyTareas(usuario: Usuario) {
//        val db = Firebase.firestore
//
//        val grupoRef = db.collection("tareas")
//        val query = grupoRef.whereEqualTo("asignedTo", usuario.id)
//
//        query.get().addOnSuccessListener { result ->
//            var cont = 1
//            for (document in result) {
//                Log.i("Tarea $cont", "Tarea: ${document.data["alias"]} || ${usuario.alias}")
//                cont++
//            }
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.message.toString())
//        }
//    }
//
//    fun updateUsuarioGrupo(usuario: Usuario){
//
//        val db = Firebase.firestore
//        val usuarioRef = db.collection("usuarios").document(usuario.id)
//
//        usuarioRef.update("listaGrupos", usuario.listaGrupos).addOnSuccessListener {
//            Log.i("Firebase", "Datos actualizados correctamente")
//        }
//    }





}