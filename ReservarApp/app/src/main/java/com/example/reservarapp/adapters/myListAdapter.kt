package com.example.reservarapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.reservarapp.R
import com.example.reservarapp.models.Usuario
import java.util.LinkedList

class myListAdapter( private val context: Context,
                     private val layout : Int,
                     private val listaUsuarios: List<Usuario>)
                    : BaseAdapter(){

    override fun getCount(): Int {
        return listaUsuarios.size
    }

    override fun getItem(position: Int): Usuario {
        return listaUsuarios[position]
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cView = convertView
        val holder : ViewHolder
        if( cView == null){

            cView = LayoutInflater.from(context).inflate(layout,null)
            holder = ViewHolder(

                cView.findViewById(R.id.bandera),
                cView.findViewById(R.id.txtUsuario)



            )
            cView.tag = holder

        } else{
            holder = cView.tag as ViewHolder
        }

        val usuarioActual = listaUsuarios[position]
        holder.icono.setImageResource(R.drawable.icono)
        holder.txtUsuario.setText(usuarioActual.alias)

        return cView!!
    }

    internal class ViewHolder(
        var icono: ImageView,
        var txtUsuario: TextView


        )


}