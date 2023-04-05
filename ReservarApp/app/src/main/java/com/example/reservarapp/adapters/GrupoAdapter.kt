package com.example.reservarapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.reservarapp.R
import com.example.reservarapp.models.Grupo

class GrupoAdapter(private val context: Context,
                   private val layout : Int,
                   private val listaGrupos: List<Grupo>)
                    : BaseAdapter(){

    override fun getCount(): Int {
        return listaGrupos.size
    }

    override fun getItem(position: Int): Grupo {
        return listaGrupos[position]
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

                cView.findViewById(R.id.imgGrupo),
                cView.findViewById(R.id.txtNombre),
                cView.findViewById(R.id.txtID)

            )
            cView.tag = holder

        } else{
            holder = cView.tag as ViewHolder
        }

        val grupoActual = listaGrupos[position]
        holder.foto.setImageResource(R.drawable.icono)
        holder.txtNombre.setText(grupoActual.alias)
        holder.txtID.setText(grupoActual.id)

        return cView!!
    }

    internal class ViewHolder(
        var foto: ImageView,
        var txtNombre: TextView,
        var txtID: TextView,


        )


}