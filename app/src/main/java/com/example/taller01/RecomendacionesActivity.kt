package com.example.taller01

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecomendacionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones)

        val recomendacion = findViewById<TextView>(R.id.textViewRecomendacion)
        val nombre = intent.getStringExtra("nombre") ?: "NA"
        val actividad = intent.getStringExtra("actividad") ?: "NA"

        recomendacion.text = "Recomendación: $nombre - $actividad"
    }
}
