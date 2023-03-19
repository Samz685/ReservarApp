package com.example.reservarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.repositories.GrupoRepo
import java.util.LinkedList

class GrupoViewModel : ViewModel() {


    private var grupoRepo = GrupoRepo()


    fun getAll() : MutableLiveData<LinkedList<Grupo>>{

        var dataGrupo = MutableLiveData<LinkedList<Grupo>>()
        grupoRepo.getAll().observeForever {
            dataGrupo.value = it
        }

        return dataGrupo
    }
}