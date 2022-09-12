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

class CrearJuego : AppCompatActivity() {
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_juego)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        var tilFechaLanzamiento = findViewById<TextInputEditText>(R.id.til_fecha_lanzamiento)
        var tilAptoTodoPublico = findViewById<TextInputEditText>(R.id.til_apto_todo_publico)
        var tilNombreJuego = findViewById<TextInputEditText>(R.id.til_nombre_juego)
        var tilHorasJuegoHistoria = findViewById<TextInputEditText>(R.id.til_horas_juego)
        var tilPrecio = findViewById<TextInputEditText>(R.id.til_precio)

        var btnAñadirJuego = findViewById<Button>(R.id.btn_crear_juego)
        btnAñadirJuego.setOnClickListener {
            var juego = hashMapOf(
                "fechaLanzamiento" to tilFechaLanzamiento.text.toString(),
                "aptoTodoPublico" to tilAptoTodoPublico.text.toString(),
                "nombre" to tilNombreJuego.text.toString(),
                "horasJuegoHistoria" to tilHorasJuegoHistoria.text.toString(),
                "precio" to tilPrecio.text.toString()
            )
            juegos.add(juego).addOnSuccessListener {
                tilFechaLanzamiento.text!!.clear()
                tilAptoTodoPublico.text!!.clear()
                tilNombreJuego.text!!.clear()
                tilHorasJuegoHistoria.text!!.clear()
                tilPrecio.text!!.clear()
                Toast.makeText(this,"Juego registrado con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Juego","Success")
            }.addOnFailureListener() {
                Log.i("Crear-Juego","Failed")
            }

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