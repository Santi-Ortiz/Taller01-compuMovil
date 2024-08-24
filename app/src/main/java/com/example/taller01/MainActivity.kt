package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        val favoritos = mutableListOf<Destino>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val botonExplorar = findViewById<Button>(R.id.btnExplorar)
        val botonFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botonRecomendaciones = findViewById<Button>(R.id.btnRecomendaciones)
        val spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)

        botonExplorar.setOnClickListener {
            openDestinosActivity(spinnerCategoria.selectedItem.toString())
        }

        botonFavoritos.setOnClickListener {
            handleFavoritosClick()
        }

        botonRecomendaciones.setOnClickListener {
            mostrarRecomendacion()
        }
    }

    private fun openDestinosActivity(categoria: String) {
        val intentDestinos = Intent(this, Destinos::class.java).apply {
            putExtra("categoria", categoria)
        }
        startActivity(intentDestinos)
    }

    private fun handleFavoritosClick() {
        if (favoritos.isEmpty()) {
            Toast.makeText(this, "No hay destinos en favoritos", Toast.LENGTH_SHORT).show()
        } else {
            val intentFavoritos = Intent(this, FavoritosActivity::class.java)
            startActivity(intentFavoritos)
        }
    }

    private fun mostrarRecomendacion() {
        if (favoritos.isEmpty()) {
            Toast.makeText(this, "NA", Toast.LENGTH_SHORT).show()
            return
        }

        val categoriaFrecuente = obtenerCategoriaFrecuente()
        val destinosDeCategoria = favoritos.filter { it.categoria == categoriaFrecuente }

        if (destinosDeCategoria.isNotEmpty()) {
            val destinoAleatorio = destinosDeCategoria.random()
            val intent = Intent(this, DescripcionFavoritos::class.java).apply {
                putExtra("nombre", destinoAleatorio.nombre)
                putExtra("pais", destinoAleatorio.pais)
                putExtra("categoria", destinoAleatorio.categoria)
                putExtra("plan", destinoAleatorio.plan)
                putExtra("precio", destinoAleatorio.precio)
                putExtra("id", destinoAleatorio.id)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "No hay destinos en la categoría más frecuente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerCategoriaFrecuente(): String {
        return favoritos
            .groupingBy { it.categoria }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key ?: "NA"
    }

    // Definición de la clase Destino
    data class Destino(
        val id: Int,
        val nombre: String,
        val pais: String,
        val categoria: String,
        val plan: String,
        val precio: Int
    )
}
