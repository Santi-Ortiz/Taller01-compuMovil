package com.example.taller01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DestinosAdapter(context: Context, private val destinos: List<Destino>) :
    ArrayAdapter<Destino>(context, 0, destinos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val destino = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_destino, parent, false)

        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val pais: TextView = view.findViewById(R.id.txtPais)

        if (destino != null) {
            nombre.text = destino.nombre
            pais.text = destino.pais
        }

        return view
    }
}