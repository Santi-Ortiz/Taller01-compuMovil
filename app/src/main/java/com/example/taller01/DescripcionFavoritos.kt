package com.example.taller01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DescripcionFavoritos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descripcion_favoritos)
        setupUI()
    }

    private fun setupUI() {
        val nombre = findViewById<TextView>(R.id.texViewNombre)
        val pais = findViewById<TextView>(R.id.texViewPais)
        val categoria = findViewById<TextView>(R.id.textViewCategoria)
        val plan = findViewById<TextView>(R.id.textViewPlan)
        val precio = findViewById<TextView>(R.id.textViewPrecio)

        val bundle = intent.extras
        if (bundle != null) {
            nombre.text = bundle.getString("nombre", "NA")
            pais.text = bundle.getString("pais", "NA")
            categoria.text = bundle.getString("categoria", "NA")
            plan.text = bundle.getString("plan", "NA")
            precio.text = "USD ${bundle.getInt("precio", 0)}"
        }
    }
}
