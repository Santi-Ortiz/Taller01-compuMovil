package com.example.taller01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Descripcion : AppCompatActivity() {
    private lateinit var btnFavoritos: Button
    private var isFavorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descripcion)

        val uiElements = initializeUI()
        btnFavoritos = findViewById(R.id.btnFavoritos)

        intent.extras?.let { bundle ->
            updateUI(bundle, uiElements)
            setupFavoriteButton(bundle)
        }
    }

    private fun initializeUI(): UIElements {
        val nombre = findViewById<TextView>(R.id.texViewNombre)
        val pais = findViewById<TextView>(R.id.texViewPais)
        val categoria = findViewById<TextView>(R.id.textViewCategoria)
        val plan = findViewById<TextView>(R.id.textViewPlan)
        val precio = findViewById<TextView>(R.id.textViewPrecio)
        return UIElements(nombre, pais, categoria, plan, precio)
    }

    private fun updateUI(bundle: Bundle, uiElements: UIElements) {
        uiElements.nombre.text = bundle.getString("nombre", "")
        uiElements.pais.text = bundle.getString("pais", "")
        uiElements.categoria.text = bundle.getString("categoria", "")
        uiElements.plan.text = bundle.getString("plan", "")
        uiElements.precio.text = "USD ${bundle.getInt("precio")}"
    }

    private fun setupFavoriteButton(bundle: Bundle) {
        btnFavoritos.setOnClickListener {
            if (!isFavorito) {
                val destino = MainActivity.Destino(
                    id = bundle.getInt("id"),
                    nombre = bundle.getString("nombre") ?: "",
                    pais = bundle.getString("pais") ?: "",
                    categoria = bundle.getString("categoria") ?: "",
                    plan = bundle.getString("plan") ?: "",
                    precio = bundle.getInt("precio")
                )

                if (!MainActivity.favoritos.contains(destino)) {
                    MainActivity.favoritos.add(destino)
                    showToast("¡Añadido a favoritos!")
                } else {
                    showToast("¡Ya está en favoritos!")
                }
                btnFavoritos.isEnabled = false
                isFavorito = true
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private data class UIElements(
        val nombre: TextView,
        val pais: TextView,
        val categoria: TextView,
        val plan: TextView,
        val precio: TextView
    )
}
