package com.example.examen_2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarJuego : AppCompatActivity() {
    var juegoSeleccionado = Juego("", "", "", "", 0, "")
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_actualizar_juego)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuego")!!

        val til_fechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        val til_aptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        val til_nombreJuego = findViewById<TextInputEditText>(R.id.til_nombre_juego)
        val til_horasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        val til_precio = findViewById<TextInputEditText>(R.id.til_precio)

        til_fechaLanzamiento.setText(juegoSeleccionado.fechaLanzamiento)
        til_aptoTodoPublico.setText(juegoSeleccionado.aptoTodoPublico)
        til_nombreJuego.setText(juegoSeleccionado.nombreJuego)
        til_horasJuegoHistoria.setText(juegoSeleccionado.horasJuegoHistoria.toString())
        til_precio.setText(juegoSeleccionado.precio)

        val btnActualizarJuego = findViewById<Button>(R.id.btn_actualizar_juego)
        btnActualizarJuego.setOnClickListener {
            juegos.document("${juegoSeleccionado.idJuego}").update(
                "fechaLanzamiento", til_fechaLanzamiento.text.toString(),
                "aptoTodoPublico", til_aptoTodoPublico.text.toString(),
                "nombreJuego", til_nombreJuego.text.toString(),
                "horasJuegoHistoria", til_horasJuegoHistoria.text.toString(),
                "precio", til_precio.text.toString()
            )
            Toast.makeText(this, "Juego actualizado exitosamente", Toast.LENGTH_SHORT).show()

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