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

class ActualizarPersonaje : AppCompatActivity() {

    var juegoSeleccionado = Juego("", "", "", "", 0, "")
    var personajeSeleccionado = Personaje("", "", "", "", "", 0, "")
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_personaje)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuegoActualizar")!!
        personajeSeleccionado = intent.getParcelableExtra<Personaje>("personaje")!!

        val til_fechaNacimiento = findViewById<TextInputEditText>(R.id.til_fecha_nacimiento)
        val til_personajePrincipal = findViewById<TextInputEditText>(R.id.til_personaje_principal)
        val til_nombrePersonaje = findViewById<TextInputEditText>(R.id.til_nombre_personaje)
        val til_edadInicial = findViewById<TextInputEditText>(R.id.til_edad)
        val til_altura = findViewById<TextInputEditText>(R.id.til_altura)

        til_fechaNacimiento.setText(personajeSeleccionado.fechaNacimiento)
        til_personajePrincipal.setText(personajeSeleccionado.personajePrincipal)
        til_nombrePersonaje.setText(personajeSeleccionado.nombrePersonaje)
        til_edadInicial.setText(personajeSeleccionado.edadIncial.toString())
        til_altura.setText(personajeSeleccionado.altura)

        val btnActualizarEditarPersonaje = findViewById<Button>(R.id.btn_actualizar_personaje)
        btnActualizarEditarPersonaje.setOnClickListener {
            juegos.document("${juegoSeleccionado.idJuego}")
                .collection("Personajes")
                .document("${personajeSeleccionado.idJuego}")
                .update(
                    "fechaNacimiento", til_fechaNacimiento.text.toString(),
                    "personajePrincipal", til_personajePrincipal.text.toString(),
                    "nombrePersonaje", til_nombrePersonaje.text.toString(),
                    "edadInicial", til_edadInicial.text.toString(),
                    "altura", til_altura.text.toString()
                )
            Toast.makeText(this, "Jugador actualizado exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InterfazPersonaje::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditarPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnCancelarEditarPersonaje.setOnClickListener{
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionJuegoActualizar", juegoSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}