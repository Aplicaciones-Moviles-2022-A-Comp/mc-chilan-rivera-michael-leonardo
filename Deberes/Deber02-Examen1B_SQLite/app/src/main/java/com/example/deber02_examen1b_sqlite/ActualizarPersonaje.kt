package com.example.deber02_examen1b_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.os.persistableBundleOf
import com.google.android.material.textfield.TextInputEditText

class ActualizarPersonaje : AppCompatActivity() {

    var posicionPersonaje = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_personaje)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

       // val idJuegoPersonaje = intent.getIntExtra("personaje",1)
        posicionPersonaje = intent.getIntExtra("posicionJuegoEditar",1)

        val til_fechaNacimiento = findViewById<TextInputEditText>(R.id.til_fecha_nacimiento)
        val til_personajePrincipal = findViewById<TextInputEditText>(R.id.til_personaje_principal)
        val til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        val til_edadInicial = findViewById<TextInputEditText>(R.id.til_edad)
        val til_altura = findViewById<TextInputEditText>(R.id.til_altura)

        var idPersonaje = intent.getIntExtra("personaje", 1)
        /*
        BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje : JuegoPersonaje ->
            if (idJuegoPersonaje == juegoPersonaje.idJuegoPersonaje){
                til_nombre.setText(juegoPersonaje.nombreJuegoPersonaje)
                idPersonaje= juegoPersonaje.idPersonaje
            }
        }
        */
        BaseDeDatos.TablaJuego!!.listarPersonajes().forEachIndexed{ indice: Int, personaje : Personaje ->
            if (personaje.idPersonaje == idPersonaje){
                til_fechaNacimiento.setText(personaje.fechaNacimiento)
                til_personajePrincipal.setText(personaje.personajePrincipal)
                til_nombre.setText(personaje.nombrePersonaje)
                til_edadInicial.setText(personaje.edadIncial.toString())
                til_altura.setText(personaje.altura)
            }
        }

        val btnActualizarEditarPersonaje = findViewById<Button>(R.id.btn_actualizar_personaje)
        btnActualizarEditarPersonaje.setOnClickListener {
            /*
            BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje: JuegoPersonaje ->
                if (idJuegoPersonaje ==        juegoPersonaje.idJuegoPersonaje){
                    Log.i("editar","${til_nombre.text.toString()}")
                    juegoPersonaje.nombreJuegoPersonaje = (til_nombre.text.toString())
                }
            }*/
            var fechaNacimiento = til_fechaNacimiento.text.toString()
            var personajePrincipal = til_personajePrincipal.text.toString()
            var nombre = til_nombre.text.toString()
            var edadInicial = til_edadInicial.text.toString()
            var altura = til_altura.text.toString()
            BaseDeDatos.TablaJuego!!.actualizarPersonaje(fechaNacimiento, personajePrincipal, nombre, edadInicial.toInt(), altura, idPersonaje)
            devolverRespuesta()
        }

        val btnCancelarEditarPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnCancelarEditarPersonaje.setOnClickListener{
            devolverRespuesta()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionJuegoeditar",posicionPersonaje)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}