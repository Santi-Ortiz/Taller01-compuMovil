package com.example.taller01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val botonExplorar = findViewById<Button>(R.id.btnExplorar)
        val spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)

        botonExplorar.setOnClickListener {
            val selectedCategory = spinnerCategoria.selectedItem.toString()
            val intentPantalla2 = Intent(this, Pantalla2::class.java).apply {
                putExtra("categoria", selectedCategory)
            }
            startActivity(intentPantalla2)
        }
    }
}