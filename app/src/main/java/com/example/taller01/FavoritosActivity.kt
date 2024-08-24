package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class FavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val listViewFavoritos: ListView = findViewById(R.id.listViewFavoritos)
        val favoritos = MainActivity.favoritos

        val nombresFavoritos = if (favoritos.isEmpty()) {
            arrayOf("No hay destinos en favoritos")
        } else {
            favoritos.map { it.nombre }.toTypedArray()
        }

        setupListView(listViewFavoritos, nombresFavoritos, favoritos)
    }

    private fun setupListView(listView: ListView, nombres: Array<String>, favoritos: List<MainActivity.Destino>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val destinoSeleccionado = favoritos[position]
            val intent = Intent(this, DescripcionFavoritos::class.java).apply {
                putExtra("nombre", destinoSeleccionado.nombre)
                putExtra("pais", destinoSeleccionado.pais)
                putExtra("categoria", destinoSeleccionado.categoria)
                putExtra("plan", destinoSeleccionado.plan)
                putExtra("precio", destinoSeleccionado.precio)
                putExtra("id", destinoSeleccionado.id)
            }
            startActivity(intent)
        }
    }
}
