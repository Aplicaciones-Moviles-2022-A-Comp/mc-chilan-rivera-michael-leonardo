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

        var longitudListaJuego = BaseDeDatosMemoria.arregloJuego.lastIndex

        BaseDeDatosMemoria.arregloJuego.forEachIndexed{ indice: Int, juego : Juego ->
            Log.i("testExamen", "${juego.idJuego} -> ${juego.nombre}")
            if (indice == longitudListaJuego){
                lastId = juego.idJuego
            }
        }
        nextId = lastId + 1

        var til_fechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        var til_aptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        var til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        var til_horasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        var til_precio = findViewById<TextInputEditText>(R.id.til_precio)

        var btnAñadirJuego = findViewById<Button>(R.id.btn_actualizar_juego)
        btnAñadirJuego.setOnClickListener {
            fechaLanzamiento = til_fechaLanzamiento.text.toString()
            aptoTodoPublico = til_aptoTodoPublico.text.toString()
            nombre = til_nombre.text.toString()
            horasJuegoHistoria = til_horasJuegoHistoria.text.toString()
            precio = til_precio.text.toString()
            BaseDeDatosMemoria.arregloJuego.add(Juego(nextId, fechaLanzamiento, aptoTodoPublico, nombre, horasJuegoHistoria, precio))
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