package com.example.reservarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.reservarapp.controllers.ClienteController
import com.example.reservarapp.controllers.ReservaController
import com.example.reservarapp.controllers.UsuarioController
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
        us1.alias = "Sam"
        us1.email = "sam@email.com"

        var us2: Usuario = Usuario()
        us2.alias = "Paul"
        us2.email = "paul@email.com"

        var gp1: Grupo = Grupo()
        gp1.id = 1
        gp1.alias = "El Pikon"
        gp1.owner = us1

        var cl: Cliente = Cliente()
        cl.nombre = "Pepito"
        cl.email = "pepito@email.com"
        cl.telefono = "622622622"

        var cl2: Cliente = Cliente()
        cl2.nombre = "Juanito"
        cl2.email = "juanito@email.com"
        cl2.telefono = "633633633"


        var re: Reserva = Reserva()
        re.cliente = cl
        re.numComensales = 4
        re.disposicion = "Terraza"
        re.comentario = "Quieren una botella de champán"
        re.grupo = gp1

        var re2: Reserva = Reserva()
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

//        leerDatos()


//        var usuarioController = UsuarioController()
//        us1.id = usuarioController.addUsuario(us1)
//        println("-----------0000000000000000000000000000000000000")
//        println("Este es el id de usuario: ${us1.id}")

        var reservaController = ReservaController()
        var clienteController = ClienteController()


        var cl3: Cliente = Cliente()
        cl3.nombre = "Petrica"
        cl3.email = "petrica@email.com"
        cl3.telefono = "622622622"
        cl3.id = clienteController.addCliente(cl3)



        var re3: Reserva = Reserva()
        re3.cliente = clienteController.getById(cl3)
        re3.numComensales = 15
        re3.disposicion = "Terraza"
        re3.comentario = "44 Cumpleaños"
        re3.grupo = gp1
        re3.id = reservaController.addReserva(re3)
        println("---------------------------zzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
        reservaController.addReserva(re3)
        println(reservaController.getAll())
        println(reservaController.getByCliente(cl3))

    }




}