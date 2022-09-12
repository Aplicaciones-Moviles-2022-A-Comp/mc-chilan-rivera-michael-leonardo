package com.example.examen_2b

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InterfazPersonaje : AppCompatActivity() {

    var juegoSeleccionado=Juego("","","","",0,"")
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Personaje>?=null
    var personajeSeleccionado:Personaje? = Personaje("","", "", "", "", 0, "")

    var resultAddNewPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuego")!!
            }
        }
    }

    var resultEditarPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuego")!!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz_personaje)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        juegoSeleccionado = intent.getParcelableExtra<Juego>("posicionJuego")!!
        listViewPersonajes()

        val tvNombreJuegoPersonaje = findViewById<TextView>(R.id.tv_nombre_juego_personaje)
        tvNombreJuegoPersonaje.setText("Juego: " + juegoSeleccionado.nombreJuego)

        val btnAddPersonaje = findViewById<Button>(R.id.btn_crear_personaje)
        btnAddPersonaje.setOnClickListener {
            abrirActividadAddPersonaje(CrearPersonaje::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_personaje, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position

        Log.i("id juegoPersonaje", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        personajeSeleccionado = adaptador!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editar_personaje -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarPersonaje(ActualizarPersonaje::class.java)
                return true
            }
            R.id.mi_eliminar_personaje -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val juegoSubColeccion= juegos.document("${juegoSeleccionado.idJuego}")
                    .collection("Personajes")
                    .document("${personajeSeleccionado!!.idPersonaje}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Personaje","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Personaje","Fallido")
                    }
                listViewPersonajes()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listViewPersonajes() {
        val juegoSubColeccion= juegos.document("${juegoSeleccionado.idJuego}")
            .collection("Personajes")
            .whereEqualTo("idJuego","${juegoSeleccionado.idJuego}")

        val lv_personajes = findViewById<ListView>(R.id.lv_personajes)
        juegoSubColeccion.get().addOnSuccessListener { result ->
            var listaPersonajes = arrayListOf<Personaje>()
            for(document in result){
                listaPersonajes.add(
                    Personaje(
                        document.id.toString(),
                        document.data.get("idJuego").toString(),
                        document.data.get("fechaNacimiento").toString(),
                        document.data.get("personajePrincipal").toString(),
                        document.data.get("nombrePersonaje").toString(),
                        document.data.get("edadInicial").toString().toInt(),
                        document.data.get("altura").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaPersonajes
            )
            lv_personajes.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_personajes)
        }
    }

    fun abrirActividadEditarPersonaje(
        clase: Class<*>
    ) {
        val intentEditarPersonaje = Intent(this, clase)
        intentEditarPersonaje.putExtra("Personaje", idItemSeleccionado)
        intentEditarPersonaje.putExtra("posicionJuegoActualizar",juegoSeleccionado)
        resultEditarPersonaje.launch(intentEditarPersonaje)
    }

    fun abrirActividadAddPersonaje(
        clase: Class<*>
    ) {
        val intentAddNewPersonaje = Intent(this, clase)
        intentAddNewPersonaje.putExtra("posicionJuego",juegoSeleccionado)
        Log.i("posicionJuego","${juegoSeleccionado}")
        resultAddNewPersonaje.launch(intentAddNewPersonaje)
    }
}