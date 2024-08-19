package com.example.taller01

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.InputStream

class Pantalla2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla2)

        val listViewLugares: ListView = findViewById(R.id.listViewLugares)

        // Obtener la categoría pasada desde MainActivity
        val selectedCategory = intent.getStringExtra("categoria") ?: ""

        // Leer el JSON desde el archivo de recursos
        val json = loadJSONFromAsset()
        val jsonObject = JSONObject(json)
        val destinosArray = jsonObject.getJSONArray("destinos")

        // Filtrar los destinos según la categoría seleccionada
        val destinos = mutableListOf<Destino>()
        for (i in 0 until destinosArray.length()) {
            val destino = destinosArray.getJSONObject(i)
            if (selectedCategory == "Todos" || destino.getString("categoria") == selectedCategory) {
                destinos.add(
                    Destino(
                        destino.getInt("id"),
                        destino.getString("nombre"),
                        destino.getString("pais"),
                        destino.getString("categoria"),
                        destino.getString("plan"),
                        destino.getInt("precio")
                    )
                )
            }
        }

        // Configurar el adaptador para el ListView
        val adapter = DestinosAdapter(this, destinos)
        listViewLugares.adapter = adapter
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream: InputStream = assets.open("destinos.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}
