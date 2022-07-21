package com.example.examen_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ActualizarPersonaje : AppCompatActivity() {

    var posicionJuego = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_personaje)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idJuegoPersonaje = intent.getIntExtra("personaje",1)
        posicionJuego = intent.getIntExtra("posicionJuegoEditar",1)

        var til_fechaNacimiento = findViewById<TextInputEditText>(R.id.til_fecha_nacimiento)
        var til_personajePrincipal = findViewById<TextInputEditText>(R.id.til_personaje_principal)
        var til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        var til_edadInicial = findViewById<TextInputEditText>(R.id.til_edad)
        var til_altura = findViewById<TextInputEditText>(R.id.til_altura)

        var idPersonaje: Int = 0

        BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje : JuegoPersonaje ->
            if (idJuegoPersonaje == juegoPersonaje.idJuegoPersonaje){
                til_nombre.setText(juegoPersonaje.nombreJuegoPersonaje)
                idPersonaje= juegoPersonaje.idPersonaje
            }
        }

        BaseDeDatosMemoria.arregloPersonaje.forEachIndexed{ indice: Int, personaje : Personaje ->
            if (idPersonaje == personaje.idPersonaje){
                til_fechaNacimiento.setText(personaje.fechaNacimiento)
                til_personajePrincipal.setText(personaje.personajePrincipal)
                til_edadInicial.setText(personaje.edadIncial)
                til_altura.setText(personaje.altura)
            }
        }

        val btnActualizarEditarPersonaje = findViewById<Button>(R.id.btn_actualizar_personaje)
        btnActualizarEditarPersonaje.setOnClickListener {
            BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje: JuegoPersonaje ->
                if (idJuegoPersonaje == juegoPersonaje.idJuegoPersonaje){
                    Log.i("editar","${til_nombre.text.toString()}")
                    juegoPersonaje.nombreJuegoPersonaje = (til_nombre.text.toString())
                }
            }
            devolverRespuesta()
        }

        val btnCancelarEditarPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnCancelarEditarPersonaje.setOnClickListener{
            devolverRespuesta()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionJuegoeditar",posicionJuego)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}