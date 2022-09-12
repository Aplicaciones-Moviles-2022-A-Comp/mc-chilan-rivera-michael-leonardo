package com.example.deber02_examen1b_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearJuego : AppCompatActivity() {
    var nextId = 0
    var lastId = 0
    var fechaLanzamiento = ""
    var aptoTodoPublico = ""
    var nombre = ""
    var horasJuegoHistoria = ""
    var precio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_juego)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        var longitudListaJuego = BaseDeDatos.TablaJuego!!.listarJuegos().lastIndex

        BaseDeDatos.TablaJuego!!.listarJuegos().forEachIndexed{ indice: Int, juego : Juego ->
            Log.i("testExamen", "${juego.idJuego} -> ${juego.nombreJuego}")
            if (indice == longitudListaJuego){
                lastId = juego.idJuego
            }
        }
        nextId = lastId + 1

        var tilFechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        var tilAptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        var tilNombreJuego = findViewById<TextInputEditText>(R.id.til_nombre_juego)
        var tilHorasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        var tilPrecio = findViewById<TextInputEditText>(R.id.til_precio)

        var btnAñadirJuego = findViewById<Button>(R.id.btn_crear_juego)
        btnAñadirJuego.setOnClickListener {
            fechaLanzamiento = tilFechaLanzamiento.text.toString()
            aptoTodoPublico = tilAptoTodoPublico.text.toString()
            nombre = tilNombreJuego.text.toString()
            horasJuegoHistoria = tilHorasJuegoHistoria.text.toString()
            precio = tilPrecio.text.toString()
            BaseDeDatos.TablaJuego!!.crearJuego(nextId, fechaLanzamiento, aptoTodoPublico, nombre, horasJuegoHistoria.toInt(), precio)
            val intentAddSuccess = Intent(this, InterfazJuego::class.java)
            startActivity(intentAddSuccess)
        }

        var btnCancelarJuego = findViewById<Button>(R.id.btn_cancelar_juego)
        btnCancelarJuego.setOnClickListener {
            val intentAddCancel = Intent(this, InterfazJuego::class.java)
            startActivity(intentAddCancel)
        }
    }
}