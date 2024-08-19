package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var botonExplorar = findViewById<Button>(R.id.btnExplorar)
        var botonFav = findViewById<Button>(R.id.btnFavoritos)
        var botonRecomendaciones = findViewById<Button>(R.id.btnRecomendaciones)

        botonExplorar.setOnClickListener {
            val intentPantalla2 = Intent(this, Pantalla2::class.java)
            startActivity(intentPantalla2)
        }
    }
}