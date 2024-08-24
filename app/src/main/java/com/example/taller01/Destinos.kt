package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class Destinos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        val listViewLugares: ListView = findViewById(R.id.listViewLugares)

        val selectedCategory = intent.getStringExtra("categoria") ?: ""
        val destinos = parseDestinos(loadJSONFromAsset(), selectedCategory)

        setupListView(listViewLugares, destinos)
    }

    private fun parseDestinos(jsonString: String?, selectedCategory: String): Array<MainActivity.Destino?> {
        val json = JSONObject(jsonString ?: "{}")
        val destinosArray = json.getJSONArray("destinos")

        return Array(destinosArray.length()) { i ->
            val destinoJson = destinosArray.getJSONObject(i)
            if (selectedCategory == "Todos" || destinoJson.getString("categoria") == selectedCategory) {
                MainActivity.Destino(
                    id = destinoJson.getInt("id"),
                    nombre = destinoJson.getString("nombre"),
                    pais = destinoJson.getString("pais"),
                    categoria = destinoJson.getString("categoria"),
                    plan = destinoJson.getString("plan"),
                    precio = destinoJson.getInt("precio")
                )
            } else {
                null
            }
        }
    }

    private fun setupListView(listViewLugares: ListView, destinos: Array<MainActivity.Destino?>) {
        val nombresFiltrados = destinos.filterNotNull().map { it.nombre }.toTypedArray()
        val destinosFiltrados = destinos.filterNotNull()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresFiltrados)
        listViewLugares.adapter = adapter

        listViewLugares.setOnItemClickListener { _, _, position, _ ->
            val destinoSeleccionado = destinosFiltrados[position]
            openDescripcionActivity(destinoSeleccionado)
        }
    }

    private fun openDescripcionActivity(destino: MainActivity.Destino) {
        val intent = Intent(this, Descripcion::class.java).apply {
            putExtra("nombre", destino.nombre)
            putExtra("pais", destino.pais)
            putExtra("categoria", destino.categoria)
            putExtra("plan", destino.plan)
            putExtra("precio", destino.precio)
        }
        startActivity(intent)
    }

    private fun loadJSONFromAsset(): String? {
        return try {
            val inputStream: InputStream = assets.open("destinos.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}
