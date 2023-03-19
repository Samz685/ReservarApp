package com.example.reservarapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.reservarapp.R
import com.example.reservarapp.models.Grupo

class ComboGrupoAdapter(context: Context, listaGrupos: List<Grupo>) :
    ArrayAdapter<Grupo>(context, 0, listaGrupos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        }

        val grupo = getItem(position)
        val textView = convertView?.findViewById<TextView>(android.R.id.text1)

        if (grupo != null) {
            textView?.text = grupo.alias
        }

        return convertView!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }

        val grupo = getItem(position)
        val textView = convertView?.findViewById<TextView>(android.R.id.text1)

        if (grupo != null) {
            textView?.text = grupo.alias
        }

        return convertView!!
    }
}




