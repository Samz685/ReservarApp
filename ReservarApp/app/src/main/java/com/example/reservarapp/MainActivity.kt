package com.example.reservarapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reservarapp.adapters.ComboGrupoAdapter
import com.example.reservarapp.adapters.myListAdapter
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.repositories.*
import com.example.reservarapp.models.Usuario
import com.example.reservarapp.viewmodels.GrupoViewModel
import com.example.reservarapp.viewmodels.UsuarioViewModel
import java.util.LinkedList

class MainActivity : AppCompatActivity() {

    private lateinit var adapterList: myListAdapter
    private lateinit var comboGrupoAdapter : ComboGrupoAdapter
    private lateinit var listview: ListView
    var listaUsuarios = LinkedList<Usuario>()
    private lateinit var btnAdd : Button
    private lateinit var etNombre: EditText
    private lateinit var etGrupo: EditText
    private lateinit var spGrupo: Spinner

    private val usuarioViewModel by lazy { ViewModelProvider(this).get(UsuarioViewModel::class.java) }
    private val grupoViewModel by lazy { ViewModelProvider(this).get(GrupoViewModel::class.java) }
    var userTemp = Usuario()
    var listaGrupos = mutableListOf<Grupo>()
    var grupoTemp = Grupo()
    var grupoActual = Grupo()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btnAdd)
        etNombre = findViewById(R.id.etNombre)
        etGrupo = findViewById(R.id.etGrupo)
        grupoActual.id = "qczbhdWGiyw5L3lnutRU"


        getAllByGroup()
        getAllGrupos()

        btnAdd.setOnClickListener{

            userTemp.alias = etNombre.text.toString()
            userTemp.listaGrupos.add(grupoTemp.id)
            usuarioViewModel.addUsuario(userTemp)
            listaUsuarios.clear()
            getAllByGroup()


        }





        inicializarAdapter()
        comboListener()


    }



    fun getAllUsuarios() {
        usuarioViewModel.getAll().observe(this, Observer {
            for (us in it) {
                listaUsuarios.add(us)
            }
            adapterList.notifyDataSetChanged()
        })
    }

    fun getAllGrupos() {
        grupoViewModel.getAll().observe(this, Observer {
            for (us in it) {
                listaGrupos.add(us)
            }
            comboGrupoAdapter.notifyDataSetChanged()

        })
    }

    fun getAllByGroup() {
        usuarioViewModel.getByGroup(grupoActual).observe(this, Observer {
            for (us in it) {
                listaUsuarios.add(us)
            }
            adapterList.notifyDataSetChanged()


        })
    }

    private fun inicializarAdapter() {

        listview = findViewById<ListView>(R.id.listView)
        adapterList = myListAdapter(this, R.layout.custom_list, listaUsuarios)
        listview.adapter = adapterList
        registerForContextMenu(listview)

        spGrupo = findViewById(R.id.spGrupo)
        comboGrupoAdapter = ComboGrupoAdapter(this, listaGrupos)
        spGrupo.adapter = comboGrupoAdapter
        registerForContextMenu(listview)


    }

    fun comboListener(){

        spGrupo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val grupoSeleccionado = parent?.getItemAtPosition(position) as Grupo
                grupoTemp = grupoSeleccionado
                println("--------------------")
                println(grupoTemp.toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                grupoTemp.alias = "Sin Grupo"
            }
        }



    }
}