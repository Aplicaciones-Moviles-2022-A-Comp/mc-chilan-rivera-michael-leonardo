package com.example.deber02_examen1b_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseDeDatos.TablaJuego = SqliteHelperJuegoPersonaje(this)
        BaseDeDatosMemoria.arregloJuegoPersonaje

        val botonVerJuegos = findViewById<Button>(R.id.btn_ver_juegos)
        botonVerJuegos.setOnClickListener {
            val intent = Intent(this, InterfazJuego::class.java)
            startActivity(intent)
        }
    }
}