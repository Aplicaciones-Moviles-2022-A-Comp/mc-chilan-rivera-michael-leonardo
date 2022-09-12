package com.example.deber02_examen1b_sqlite

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

class InterfazPersonaje : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idJuegoDueño = 0
    var posicionJuego = 0
    var selectedItem = 0

    var personajeLista = arrayListOf<String>()
    var idJuegoPersonaje = arrayListOf<Int>()

    var resultAddNewPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionJuego = data?.getIntExtra("posicionJuego",0)!!
            }
        }

    }

    var resultEditarPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionJuego = data?.getIntExtra("posicionJuegoAEditar",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz_personaje)
    }

    fun listViewPersonajes():ArrayList<Personaje> {
        var listaIDPersonajes = arrayListOf<Int>()

        BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed { indice: Int, personajeJuego: JuegoPersonaje ->
            if (personajeJuego.idJuego == idJuegoDueño) {
                listaIDPersonajes.add(personajeJuego.idPersonaje)
            }
        }
        var listaPersonajes = arrayListOf<Personaje>()
        BaseDeDatos.TablaPersonaje!!.listarPersonajes()
            .forEachIndexed { indice: Int, personaje: Personaje ->
                for (i in listaIDPersonajes) {
                    if (i == personaje.idPersonaje) {
                        listaPersonajes.add(personaje)
                    }
                }
            }
        return listaPersonajes
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        //personajeLista = arrayListOf()
        //idJuegoPersonaje = arrayListOf()
        posicionJuego = intent.getIntExtra("posicionEditar",1)
        //Log.i("posJuego","${posicionJuego}")
        val tvNombreJuegoPersonaje = findViewById<TextView>(R.id.tv_nombre_juego_personaje)

        BaseDeDatos.TablaJuego!!.listarJuegos().forEachIndexed{ indice: Int, juego : Juego ->
            Log.i("testExamen","${juego.idJuego} -> ${juego.nombreJuego}")
            if (indice == posicionJuego){
                idJuegoDueño = juego.idJuego
                var label = "Juego: ${juego.nombreJuego}"
                tvNombreJuegoPersonaje.setText(label)
            }
        }

        /*BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje : JuegoPersonaje ->
            if (idJuegoDueño == juegoPersonaje.idJuego){
                personajeLista.add(juegoPersonaje.nombreJuegoPersonaje.toString())
                idJuegoPersonaje.add(juegoPersonaje.idJuegoPersonaje)
            }
        }*/

        val listViewPersonaje = findViewById<ListView>(R.id.lv_personajes)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewPersonajes()
        )
        listViewPersonaje.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnAddPersonaje = findViewById<Button>(R.id.btn_crear_personaje)
        btnAddPersonaje.setOnClickListener {
            abrirActividadAddPersonaje(CrearPersonaje::class.java)
        }

        val btnAtrasPersonaje = findViewById<Button>(R.id.btn_cancelar_personaje)
        btnAtrasPersonaje.setOnClickListener {
            val intentAtrasPersonaje = Intent(this, InterfazJuego::class.java)
            startActivity(intentAtrasPersonaje)
        }

        this.registerForContextMenu(listViewPersonaje)

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
        val id = info.position
        selectedItem = id
        idItemSeleccionado = idJuegoPersonaje.elementAt(id)
        Log.i("id juegoPersonaje", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_personaje -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarPersonaje(ActualizarPersonaje::class.java)
                return true
            }
            R.id.mi_eliminar_personaje -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                BaseDeDatos.TablaPersonaje!!.eliminarPersonaje(idItemSeleccionado)
                val listViewJugador = findViewById<ListView>(R.id.lv_personajes)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listViewPersonajes()
                )
                listViewJugador.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarPersonaje(
        clase: Class<*>
    ) {
        val intentEditarPersonaje = Intent(this, clase)
        intentEditarPersonaje.putExtra("personaje", idItemSeleccionado)
        intentEditarPersonaje.putExtra("posicionJuegoEditar",posicionJuego)
        resultEditarPersonaje.launch(intentEditarPersonaje)
    }

    fun abrirActividadAddPersonaje(
        clase: Class<*>
    ) {
        val intentAddNewPersonaje = Intent(this, clase)
        intentAddNewPersonaje.putExtra("posicionJuegp",posicionJuego)
        Log.i("positionSend","${posicionJuego}")
        resultAddNewPersonaje.launch(intentAddNewPersonaje)
    }

    fun eliminarPersonaje(
        idPersonajeAEliminar: Int
    ){
        val listViewPersonaje = findViewById<ListView>(R.id.lv_personajes)

        var auxListaJuegoPersonaje = arrayListOf<JuegoPersonaje>()

        BaseDeDatosMemoria.arregloJuegoPersonaje.forEach{ juegoPersonaje:JuegoPersonaje ->
            if(idPersonajeAEliminar != juegoPersonaje.idJuegoPersonaje){
                auxListaJuegoPersonaje.add(juegoPersonaje)
            }
        }

        BaseDeDatosMemoria.arregloJuegoPersonaje = auxListaJuegoPersonaje

        personajeLista.removeAt(selectedItem)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            personajeLista
        )
        listViewPersonaje.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}