package com.example.reservarapp.pantallas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.reservarapp.R
import com.example.reservarapp.models.Solicitud
import com.example.reservarapp.viewmodels.SolicitudViewModel

class Solicitud_Crear : AppCompatActivity() {

    private val solicitudViewModel by lazy { ViewModelProvider(this).get(SolicitudViewModel::class.java) }
    private lateinit var itextGrupo : EditText
    private lateinit var btnEnviar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud_crear)

        var txtGrupo = ""

        itextGrupo = findViewById(R.id.itxtGrupo)
        btnEnviar = findViewById(R.id.btnEnviar)


        btnEnviar.setOnClickListener{

            txtGrupo = itextGrupo.text.toString()
            var solicitud = Solicitud()
            solicitud.toGroup = txtGrupo
            solicitud.fromUser = "Sam"
            solicitud.estado = "Pendiente"
            solicitud.foto = R.drawable.icono
            solicitudViewModel.addSolicitud(solicitud)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }
    }
}