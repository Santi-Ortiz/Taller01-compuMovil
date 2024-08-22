package com.example.taller01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class Descripcion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descripcion)

        val nombre = findViewById<TextView>(R.id.texViewNombre)
        val pais = findViewById<TextView>(R.id.texViewPais)
        val categoria = findViewById<TextView>(R.id.textViewCategoria)
        val plan = findViewById<TextView>(R.id.textViewPlan)
        val precio = findViewById<TextView>(R.id.textViewPrecio)
        val btnFavoritos = findViewById<Button>(R.id.btnFavoritos)

        val bundle = intent.extras
        if (bundle != null) {
            nombre.text = bundle.getString("nombre")
            pais.text = bundle.getString("pais")
            categoria.text = bundle.getString("categoria")
            plan.text = bundle.getString("plan")
            precio.text = "USD " + bundle.getInt("precio")

            btnFavoritos.setOnClickListener {
                Toast.makeText(this,"¡Añadido a favoritos!",Toast.LENGTH_SHORT).show()
            }

        }
    }
}