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

class CrearPersonaje : AppCompatActivity() {
    var juegoSeleccionado = Juego("", "", "", "", 0, "")
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")
    val personajes = db.collection("Personajes")
    var idJugadorSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo_vida", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuego")!!
        val juegoSubColeccion = juegos.document("${juegoSeleccionado.idJuego}")
            .collection("Personajes")

        var til_fechaNacimiento = findViewById<TextInputEditText>(R.id.til_fecha_nacimiento)
        var til_personajePrincipal = findViewById<TextInputEditText>(R.id.til_personaje_principal)
        var til_nombre = findViewById<TextInputEditText>(R.id.til_nombre_personaje)
        var til_edadInicial = findViewById<TextInputEditText>(R.id.til_edad)
        var til_altura = findViewById<TextInputEditText>(R.id.til_altura)

        Log.i("posicionJuego", "${juegoSeleccionado.idJuego}")

        var btnAniadirPersonaje = findViewById<Button>(R.id.btn_crear_personaje)
        btnAniadirPersonaje.setOnClickListener {
            var personaje = hashMapOf(
                "idJuego" to juegoSeleccionado.idJuego,
                "fechaNacimiento" to til_fechaNacimiento.text.toString(),
                "personajePrincipal" to til_personajePrincipal.text.toString(),
                "nombrePersonaje" to til_nombre.text.toString(),
                "edadInicial" to til_edadInicial.text.toString(),
                "altura" to til_altura.text.toString()
            )
            juegoSubColeccion.add(personaje).addOnSuccessListener {
                Toast.makeText(this, "Personaje registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Personaje", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-Personaje", "Fallido")
            }
            val intentAddSucces = Intent(this, InterfazPersonaje::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnCancelarPersonaje.setOnClickListener {
            devolverRespuesta()
        }
    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionJuego", juegoSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}