package com.example.reservarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservarapp.controllers.*
import com.example.reservarapp.models.Cliente
import com.example.reservarapp.models.Grupo
import com.example.reservarapp.models.Reserva
import com.example.reservarapp.models.Usuario

class MainActivity : AppCompatActivity() {

    var listaReservas = mutableListOf<Reserva>()
    var reservaController = ReservaController()
    var clienteController = ClienteController()
    var usuarioController = UsuarioController()
    var grupoController = GrupoController()
    var tareaController = TareaController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaReservas = mutableListOf<Reserva>()


                //creacion GRUPOS

        var gp3: Grupo = Grupo()
        gp3.alias = "Grupo azul"
        gp3.id = "pmQktWkb0QeHiLXFHYZq"

//        var gp4: Grupo = Grupo()
//        gp4.alias = "Grupo verde"
//        gp4.id = grupoController.addGrupo(gp4)
//
//        var gp5: Grupo = Grupo()
//        gp5.alias = "Grupo naranja"
//        gp5.id = grupoController.addGrupo(gp5)
//
//        var gp6: Grupo = Grupo()
//        gp6.alias = "Grupo rojo"
//        gp6.id = grupoController.addGrupo(gp6)


        //Creacion USUARIOS

//        var us1: Usuario = Usuario()
//        us1.alias = "Sam Galvan"
//        us1.email = "sam@email.com"
//        us1.password = "1234"
//        us1.listaGrupos.add(gp3.id)
//        us1.listaGrupos.add(gp4.id)
//        us1.id = usuarioController.addUsuario(us1)
//
//        var us2: Usuario = Usuario()
//        us2.alias = "Paul Negru"
//        us2.email = "paul@email.com"
//        us2.password = "4321"
//        us2.listaGrupos.add(gp5.id)
//        us2.listaGrupos.add(gp6.id)
//        us2.id = usuarioController.addUsuario(us2)
//
//        usuarioController.findMyGroups(us1)
//        usuarioController.findMyGroups(us2)

        var us2: Usuario = Usuario()
        us2.alias = "Javier Galvan"
        us2.listaGrupos.add(gp3.id)
        us2.id = "3llXheOwVsyM89q2C9vY"
        var us3: Usuario = Usuario()
//
//        us3.alias = "Ismael Galvan"
//        us3.listaGrupos.add("T1MTlryKoiq1xjl2x4tz")
//        us3.id = usuarioController.addUsuario(us3)
//
//        var grupo = Grupo()
//        grupo.id = "T1MTlryKoiq1xjl2x4tz"
//        grupoController.findIntegrants(grupo)

        //borrar Integrante grupo

        grupoController.deleteIntegrant(us2,"T1MTlryKoiq1xjl2x4tz")
//
//        //creacion GRUPOS
//
//        var gp1: Grupo = Grupo()
//        gp1.alias = "Banana Beach"
//        gp1.owner = us1.id
//        gp1.id = grupoController.addGrupo(gp1)
//
//        var gp2: Grupo = Grupo()
//        gp2.alias = "El Pikon"
//        gp2.owner = us2.id
//        gp2.id = grupoController.addGrupo(gp2)
//
//        //creacion CLIENTES
//
//        var cl1: Cliente = Cliente()
//        cl1.alias = "Pepito"
//        cl1.email = "pepito@email.com"
//        cl1.telefono = "622622622"
//        cl1.id = clienteController.addCliente(cl1)
//
//        var cl2: Cliente = Cliente()
//        cl2.alias = "Juanito"
//        cl2.email = "juanito@email.com"
//        cl2.telefono = "633633633"
//        cl2.id = clienteController.addCliente(cl2)
//
//        //creacion RESERVAS
//
//        var re1: Reserva = Reserva()
//        re1.cliente = cl1.id
//        re1.numComensales = 4
//        re1.disposicion = "Terraza"
//        re1.comentario = "Quieren una botella de champán"
//        re1.grupo = gp1.id
//        re1.id = reservaController.addReserva(re1)
//
//        var re2: Reserva = Reserva()
//        re2.cliente = cl2.id
//        re2.numComensales = 6
//        re2.disposicion = "Interior"
//        re2.comentario = "Celebracion de cumpleaños"
//        re2.grupo = gp2.id
//        re2.id = reservaController.addReserva(re2)


    }
}