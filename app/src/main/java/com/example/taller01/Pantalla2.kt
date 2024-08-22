package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.provider.BlockedNumberContract.BlockedNumbers
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.IOException
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
                        destino.getInt("id"),destino.getString("nombre"),destino.getString("pais"),destino.getString("categoria"), destino.getString("plan"),                        destino.getInt("precio")
                    )
                )
            }
        }

        // Se le asigna un adaptador a ListView
        val adapter = DestinosAdapter(this, destinos)
        listViewLugares.adapter = adapter

        listViewLugares.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val posicion: Any = listViewLugares.getItemAtPosition(position)
            posicion as Destino
            val bundle = Bundle()
            bundle.putString("nombre", posicion.nombre)
            bundle.putString("pais", posicion.pais)
            bundle.putString("categoria", posicion.categoria)
            bundle.putString("plan", posicion.plan)
            bundle.putInt("precio", posicion.precio)
            val intent = Intent(this, Descripcion::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val isStream: InputStream = assets.open("destinos.json")
            val size:Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
