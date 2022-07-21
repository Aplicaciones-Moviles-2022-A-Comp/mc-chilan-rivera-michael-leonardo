package com.example.examen_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ActualizarJuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_actualizar_juego)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionJuego = intent.getIntExtra("posicionEditar",1)

        var til_fechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        var til_aptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        var til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        var til_horasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        var til_precio = findViewById<TextInputEditText>(R.id.til_precio)

        BaseDeDatosMemoria.arregloJuego.forEachIndexed{ indice: Int, juego : Juego ->
            Log.i("testExamen","${juego.idJuego} -> ${juego.nombre}")
            if (indice == posicionJuego){
                til_fechaLanzamiento.setText(juego.fechaLanzamiento)
                til_aptoTodoPublico.setText(juego.aptoTodoPublico)
                til_nombre.setText(juego.nombre)
                til_horasJuegoHistoria.setText(juego.horasJuegoHistoria)
                til_precio.setText(juego.precio)
            }
        }

        val btnActualizarJuego = findViewById<Button>(R.id.btn_actualizar_juego)
        btnActualizarJuego.setOnClickListener {
            BaseDeDatosMemoria.arregloJuego.forEachIndexed{ indice: Int, juego: Juego ->
                if (indice == posicionJuego){
                    Log.i("editar","${til_nombre.text.toString()}")
                    juego.fechaLanzamiento = (til_fechaLanzamiento.text.toString())
                    juego.aptoTodoPublico = (til_aptoTodoPublico.text.toString())
                    juego.nombre = (til_nombre.text.toString())
                    juego.horasJuegoHistoria = (til_horasJuegoHistoria.text.toString())
                    juego.precio = (til_precio.text.toString())
                }
            }
            val intentEditSucces = Intent(this, InterfazJuego::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_juego)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InterfazJuego::class.java)
            startActivity(intentEditCancel)
        }

    }
}