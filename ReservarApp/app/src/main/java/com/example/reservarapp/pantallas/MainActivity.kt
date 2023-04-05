package com.example.reservarapp.pantallas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reservarapp.R
import com.example.reservarapp.adapters.ComboGrupoAdapter
import com.example.reservarapp.adapters.SolicitudAdapter
import com.example.reservarapp.adapters.UsuarioAdapter
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Solicitud
import com.example.reservarapp.repositories.*
import com.example.reservarapp.models.Usuario
import com.example.reservarapp.viewmodels.GrupoViewModel
import com.example.reservarapp.viewmodels.SolicitudViewModel
import com.example.reservarapp.viewmodels.UsuarioViewModel
import java.util.LinkedList

class MainActivity : AppCompatActivity() {

    private lateinit var adapterList: SolicitudAdapter
    private lateinit var comboGrupoAdapter : ComboGrupoAdapter
    private lateinit var listview: ListView
    var listaUsuarios = LinkedList<Usuario>()
    private lateinit var btnAdd : Button
    private lateinit var btnUpdate : Button
    private lateinit var etNombre: EditText
    private lateinit var etGrupo: EditText
    private lateinit var spGrupo: Spinner

    private lateinit var btnCrearSolicitud : Button

    private val usuarioViewModel by lazy { ViewModelProvider(this).get(UsuarioViewModel::class.java) }
    private val grupoViewModel by lazy { ViewModelProvider(this).get(GrupoViewModel::class.java) }
    private val solicitudViewModel by lazy { ViewModelProvider(this).get(SolicitudViewModel::class.java) }
    var userTemp = Usuario()
    var userActual = Usuario()
    var listaGrupos = mutableListOf<Grupo>()
    var solicitudActual = Solicitud()
    var grupoActual = Grupo()
    var grupoSeleccionado = Grupo()


    var listaSolicitudes = mutableListOf<Solicitud>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        etNombre = findViewById(R.id.etNombre)
        etGrupo = findViewById(R.id.etGrupo)
//        grupoActual.id = "qczbhdWGiyw5L3lnutRU"

        btnCrearSolicitud = findViewById(R.id.btnCrearSolicitud)
        grupoActual.alias = "El Pikon"
        grupoActual.foto = R.drawable.icono


        getSolicitudesByGroup()

//        getAllUsuarios()
//        getAllGrupos()

        grupoActual.alias = "El Pikon"
        grupoActual.foto = R.drawable.icono
        btnCrearSolicitud.setOnClickListener {
            val intent = Intent(this, Solicitud_Crear::class.java)
            startActivity(intent)
        }


        btnAdd.setOnClickListener{


            solicitudActual.estado = etNombre.text.toString()
            solicitudViewModel.updateSolicitud(solicitudActual)
            grupoActual.listaMiembros.add(solicitudActual.fromUser)
            grupoViewModel.addGrupo(grupoActual)


            adapterList.notifyDataSetChanged()



        }

        btnUpdate.setOnClickListener{




            var grupoID = grupoViewModel.addGrupo(grupoActual)



        }





        inicializarAdapter()
        seleccionarSolicitud()



    }


    private fun inicializarAdapter() {

        listview = findViewById<ListView>(R.id.listView)
        adapterList = SolicitudAdapter(this, R.layout.custom_solicitud, listaSolicitudes)
        listview.adapter = adapterList
        registerForContextMenu(listview)

    }

//    fun getAllSolicitudes() {
//        solicitudViewModel.getAll().observe(this, Observer {
//            for (us in it) {
//                listaSolicitudes.add(us)
//            }
//            adapterList.notifyDataSetChanged()
//        })
//    }

    fun getSolicitudesByGroup() {

        solicitudViewModel.getByGroup(grupoActual).observe(this, Observer {
            for (us in it) {
                listaSolicitudes.add(us)
            }
            adapterList.notifyDataSetChanged()
        })
    }

    fun seleccionarSolicitud() {

        listview.setOnItemClickListener() { adapterView, view, position, id ->

            solicitudActual = listaSolicitudes[position]
            etNombre.setText(solicitudActual.estado.toString())

            grupoSeleccionado = grupoViewModel.findById(listaSolicitudes[position].toGroup) as Grupo

        }
        registerForContextMenu(listview)


    }


//    fun getAllUsuarios() {
//        usuarioViewModel.getAll().observe(this, Observer {
//            for (us in it) {
//                listaUsuarios.add(us)
//            }
//            adapterList.notifyDataSetChanged()
//        })
//    }

//    fun getAllGrupos() {
//        grupoViewModel.getAll().observe(this, Observer {
//            for (us in it) {
//                listaGrupos.add(us)
//            }
//            comboGrupoAdapter.notifyDataSetChanged()
//
//        })
//    }

//    fun getAllByGroup() {
//        usuarioViewModel.getByGroup(grupoActual).observe(this, Observer {
//            for (us in it) {
//                listaUsuarios.add(us)
//            }
//            adapterList.notifyDataSetChanged()
//
//
//        })
//    }

//    private fun inicializarAdapter() {
//
//        listview = findViewById<ListView>(R.id.listView)
//        adapterList = UsuarioAdapter(this, R.layout.custom_usuario, listaUsuarios)
//        listview.adapter = adapterList
//        registerForContextMenu(listview)
//
//        spGrupo = findViewById(R.id.spGrupo)
//        comboGrupoAdapter = ComboGrupoAdapter(this, listaGrupos)
//        spGrupo.adapter = comboGrupoAdapter
//        registerForContextMenu(listview)
//
//
//    }

//    fun comboListener(){
//
//        spGrupo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val grupoSeleccionado = parent?.getItemAtPosition(position) as Grupo
//                grupoTemp = grupoSeleccionado
//                println("--------------------")
//                println(grupoTemp.toString())
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                grupoTemp.alias = "Sin Grupo"
//            }
//        }
//    }

//    fun mostrarMensaje() {
//
//        listview.setOnItemClickListener() { adapterView, view, position, id ->
//
//            Toast.makeText(
//                this,
//                "Yo soy ${listaUsuarios[position].alias}",
//                Toast.LENGTH_SHORT
//            ).show()
//            userActual = listaUsuarios[position]
//
//
//
//        }
//        registerForContextMenu(listview)
//    }
}