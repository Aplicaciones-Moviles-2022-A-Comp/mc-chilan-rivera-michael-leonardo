package com.example.examen_1b_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActualizarJuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_juego)
        val nombre = intent.getStringExtra("nombreSO")
        val idSOE = intent.getIntExtra("idSO", 0)
        val nombreActual = findViewById<TextView>(R.id.)
        nombreActual.setText(nombre)
        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_editar_nombre_so)
        val btnActualizarNombreSO = findViewById<Button>(R.id.btn_actualizar_nombre_so)
        val inputNombreSO = findViewById<EditText>(R.id.input_actualizar_nombre_so)

        btnCancelar.setOnClickListener{
            inputNombreSO.setText("")
            irActividad(MainActivity::class.java)
        }
        btnActualizarNombreSO.setOnClickListener{
            BBaseDatosMemoria.TablaSO!!.actualizarSO(inputNombreSO.text.toString(),idSOE)
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}.