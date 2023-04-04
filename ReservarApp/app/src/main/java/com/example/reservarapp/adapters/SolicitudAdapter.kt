package com.example.reservarapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.reservarapp.R
import com.example.reservarapp.models.Solicitud

class SolicitudAdapter(private val context: Context,
                       private val layout : Int,
                       private val listaSolicitudes: List<Solicitud>)
                    : BaseAdapter(){

    override fun getCount(): Int {
        return listaSolicitudes.size
    }

    override fun getItem(position: Int): Solicitud {
        return listaSolicitudes[position]
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

                cView.findViewById(R.id.imgUsuario),
                cView.findViewById(R.id.txtFrom),
                cView.findViewById(R.id.txtTo),
                cView.findViewById(R.id.txtEstado)

            )
            cView.tag = holder

        } else{
            holder = cView.tag as ViewHolder
        }

        val solicitudActual = listaSolicitudes[position]
        holder.foto.setImageResource(R.drawable.icono)
        holder.txtFrom.setText(solicitudActual.fromUser)
        holder.txtTo.setText(solicitudActual.toGroup)
        holder.txtEstado.setText(solicitudActual.estado)

        return cView!!
    }

    internal class ViewHolder(
        var foto: ImageView,
        var txtFrom: TextView,
        var txtTo: TextView,
        var txtEstado: TextView


        )


}