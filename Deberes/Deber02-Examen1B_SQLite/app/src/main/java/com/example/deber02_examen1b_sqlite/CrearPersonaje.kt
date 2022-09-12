package com.example.deber02_examen1b_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearPersonaje : AppCompatActivity() {

    var nextId = 0
    var lastId = 0

    var nextIdJP = 0
    var lastIdJP = 0
    var posicionJuego = 0
    var idJuegoDuenio = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo_vida", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        posicionJuego = intent.getIntExtra("posicionJuego", -1)

        Log.i("posJuego", "${posicionJuego}")

        /*val personajesDisponibles = arrayListOf<String>()

        BaseDeDatosMemoria.arregloPersonaje.forEachIndexed{ indice: Int, personaje : Personaje ->
            //Log.i("testExamen", "${personaje.idPersonaje} -> ${personaje.nombre}")
            //personajesDisponibles.add(personaje.fechaNacimiento.toString())
            //personajesDisponibles.add(personaje.personajePrincipal.toString())
            personajesDisponibles.add(personaje.nombre.toString())
            //personajesDisponibles.add(personaje.edadIncial.toString())
            //personajesDisponibles.add(personaje.altura.toString())
        }*/
        BaseDeDatos.TablaJuego!!.listarJuegos().forEachIndexed{ indice: Int, juego : Juego ->
            //Log.i("testExamen", "${personaje.idPersonaje} -> ${personaje.nombre}")
            if (indice == posicionJuego) {
                idJuegoDuenio = juego.idJuego
            }
        }

        var longitudListaPersonajes = BaseDeDatos.TablaJuego!!.listarPersonajes().lastIndex

        BaseDeDatos.TablaJuego!!.listarPersonajes().forEachIndexed{ indice: Int, personaje : Personaje ->
            Log.i("testExamen", "${personaje.idPersonaje} -> ${personaje.nombrePersonaje}")
            if (indice == longitudListaPersonajes) {
                lastId = personaje.idPersonaje
            }
        }

        nextId = lastId + 1


        var longitudJuegoPersonaje = BaseDeDatosMemoria.arregloJuegoPersonaje.lastIndex
        BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{indice: Int, personajeJuego:JuegoPersonaje ->
            if(indice==longitudJuegoPersonaje){
                lastIdJP=personajeJuego.idJuegoPersonaje
            }
        }

        nextIdJP = lastIdJP + 1

        var til_fechaNacimiento = findViewById<TextInputEditText>(R.id.til_fecha_nacimiento)
        var til_personajePrincipal = findViewById<TextInputEditText>(R.id.til_personaje_principal)
        var til_nombre = findViewById<TextInputEditText>(R.id.til_nombre)
        var til_edadInicial = findViewById<TextInputEditText>(R.id.til_edad)
        var til_altura = findViewById<TextInputEditText>(R.id.til_altura)

        var btnAñadirPersonaje = findViewById<Button>(R.id.btn_crear_personaje)
        btnAñadirPersonaje.setOnClickListener {
            var fechaNacimiento = til_fechaNacimiento.text.toString()
            var personajePrincipal = til_personajePrincipal.text.toString()
            var nombre = til_nombre.text.toString()
            var edadInicial = til_edadInicial.text.toString()
            var altura = til_altura.text.toString()
            BaseDeDatosMemoria.arregloJuegoPersonaje.add(JuegoPersonaje(nextIdJP, nombre, idJuegoDuenio, nextId))
                //(nextId, fechaNacimiento, personajePrincipal, nombre, edadInicial, altura))
            BaseDeDatos.TablaJuego!!.crearPersonaje(nextId, fechaNacimiento, personajePrincipal, nombre, edadInicial.toInt(), altura)
            devolverRespuesta()
        }

        var btnCancelarPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnCancelarPersonaje.setOnClickListener {
            devolverRespuesta()
        }
    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionJuego", posicionJuego)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}