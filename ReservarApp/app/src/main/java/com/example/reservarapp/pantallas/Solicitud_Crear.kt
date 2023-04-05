package com.example.reservarapp.pantallas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reservarapp.R
import com.example.reservarapp.adapters.GrupoAdapter
import com.example.reservarapp.adapters.SolicitudAdapter
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Solicitud
import com.example.reservarapp.models.Usuario
import com.example.reservarapp.viewmodels.GrupoViewModel
import com.example.reservarapp.viewmodels.SolicitudViewModel

class Solicitud_Crear : AppCompatActivity() {

    private val solicitudViewModel by lazy { ViewModelProvider(this).get(SolicitudViewModel::class.java) }
    private val grupoViewModel by lazy { ViewModelProvider(this).get(GrupoViewModel::class.java) }
    private lateinit var itextGrupo : EditText
    private lateinit var btnEnviar : Button
    private lateinit var btnVolver : Button
    private lateinit var listviewGrupos : ListView
    private lateinit var adapterList: GrupoAdapter
    var listaGruposAdmitido = mutableListOf<Grupo>()
    var userSam = Usuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud_crear)

        var txtGrupo = ""



        itextGrupo = findViewById(R.id.itxtGrupo)
        btnEnviar = findViewById(R.id.btnEnviar)
        btnVolver = findViewById(R.id.btnVolver)


        userSam.id = "seuDd7Kr6EYX38zP0dmc"
        userSam.alias = "Sam Galvan"
        userSam.email = "sam@gmail.com"

        getAllmine()
        inicializarAdapter()


        btnEnviar.setOnClickListener{

            txtGrupo = itextGrupo.text.toString()
            var solicitud = Solicitud()
            solicitud.toGroup = txtGrupo
            solicitud.fromUser = userSam.id
            solicitud.estado = "Pendiente"
            solicitud.foto = R.drawable.icono
            solicitudViewModel.addSolicitud(solicitud)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun inicializarAdapter() {
        listviewGrupos = findViewById<ListView>(R.id.listview_Grupos)
        adapterList = GrupoAdapter(this, R.layout.custom_grupo, listaGruposAdmitido)
        listviewGrupos.adapter = adapterList
        registerForContextMenu(listviewGrupos)
    }

    private fun getAllmine(){

        grupoViewModel.getAllmine(userSam).observe(this, Observer {
            for (us in it) {
                listaGruposAdmitido.add(us)
            }
            adapterList.notifyDataSetChanged()
        })


    }
}