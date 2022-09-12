package com.example.deber02_examen1b_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

        val til_fechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        val til_aptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        val til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        val til_horasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        val til_precio = findViewById<TextInputEditText>(R.id.til_precio)

        BaseDeDatos.TablaJuego!!.listarJuegos().forEachIndexed{ indice: Int, juego : Juego ->
            Log.i("testExamen","${juego.idJuego} -> ${juego.nombreJuego}")
            if (indice == posicionJuego){
                til_fechaLanzamiento.setText(juego.fechaLanzamiento)
                til_aptoTodoPublico.setText(juego.aptoTodoPublico)
                til_nombre.setText(juego.nombreJuego)
                til_horasJuegoHistoria.setText(juego.horasJuegoHistoria.toString())
                til_precio.setText(juego.precio)
            }
        }

        val btnActualizarJuego = findViewById<Button>(R.id.btn_actualizar_juego)
        btnActualizarJuego.setOnClickListener {
            /*BaseDeDatos.TablaJuego!!.listarJuegos().forEachIndexed{ indice: Int, juego: Juego ->
                if (indice == posicionJuego){
                    Log.i("editar","${til_nombre.text.toString()}")
                    juego.fechaLanzamiento = (til_fechaLanzamiento.text.toString())
                    juego.aptoTodoPublico = (til_aptoTodoPublico.text.toString())
                    juego.nombreJuego = (til_nombre.text.toString())
                    juego.horasJuegoHistoria = (til_horasJuegoHistoria.text.toString()).toInt()
                    juego.precio = (til_precio.text.toString())
                }
            }*/
            Log.i("editar","${til_nombre.text.toString()}")
            var fechaLanzamiento = til_fechaLanzamiento.text.toString()
            var aptoTodoPublico = til_aptoTodoPublico.text.toString()
            var nombre = til_nombre.text.toString()
            var horasJuegoHistoria = til_horasJuegoHistoria.text.toString()
            var precio = til_precio.text.toString()
            BaseDeDatos.TablaJuego!!.actualizarJuego(fechaLanzamiento, aptoTodoPublico, nombre, horasJuegoHistoria.toInt(), precio, posicionJuego)
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