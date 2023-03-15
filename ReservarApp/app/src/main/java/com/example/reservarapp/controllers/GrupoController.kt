package com.example.reservarapp.controllers

import android.util.Log
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class GrupoController {

    val db = Firebase.firestore

    fun addGrupo(grupo: Grupo) : String {

        val db = Firebase.firestore
        val grupoRef = db.collection("grupos").document()
        grupo.id = grupoRef.id


        val datos = hashMapOf(
            "id" to grupo.id,
            "alias" to grupo.alias,
            "owner" to grupo.owner,
            "admin" to grupo.admin,
            "bloqueados" to grupo.bloqueados
        )
        grupoRef.set(datos).addOnSuccessListener {
            Log.i("Firebase", "Datos insertados correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
        return grupo.id
    }

    fun findByOwner(usuario: Usuario) {

        val grupoRef = db.collection("grupos")
        val query = grupoRef.whereEqualTo("owner", usuario.id)

        query.get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i("Grupo 1", "Grupo: ${document.data["alias"]} || ${document.data["owner"]}")
            }

        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun findIntegrants(grupo: Grupo) {
        val db = Firebase.firestore

        val usuariosRef = db.collection("usuarios")
        val query = usuariosRef.whereArrayContains("listaGrupos", grupo.id)

        query.get().addOnSuccessListener { result ->
            var cont = 1
            for (document in result) {
                Log.i("Integrante $cont", "Usuario: ${document.data["alias"]}")
                cont++
            }
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun findById(id: String) {

        val grupoRef = db.collection("grupos")
        val query = grupoRef.whereEqualTo("owner", id)

        query.get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i("Grupo 1", "Grupo: ${document.data["alias"]} || Owner: ${document.data["owner"]} || ID_grupo ${document.data["id"]}")
            }

        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }
    }

    fun deleteIntegrant(usuario: Usuario, id_grupo: String) {

        var usuarioController = UsuarioController()
        val usuarioRef = db.collection("usuarios")
        val query = usuarioRef.whereEqualTo("id", usuario.id)
        val listaGrupos = mutableListOf<String>()
        val listaBloqueados = mutableListOf<String>()
        var user = Usuario()

        query.get().addOnSuccessListener { result ->
            for (document in result) {
                user = document.toObject<Usuario>()
                }
            for (grupo in user.listaGrupos){
                if(grupo != id_grupo){
                    listaGrupos.add(grupo)
                }
            }
            user.listaGrupos = listaGrupos
            usuarioController.updateUsuarioGrupo(user)





        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.message.toString())
        }

    }

    fun addUsuarioBloqueado(grupo: Grupo){

        val db = Firebase.firestore
        val grupoRef = db.collection("grupos").document(grupo.id)

        grupoRef.update("bloqueados", grupo.bloqueados).addOnSuccessListener {
            Log.i("Firebase", "Datos actualizados correctamente")
        }
    }





}