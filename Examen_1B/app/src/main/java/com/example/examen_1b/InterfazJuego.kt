package com.example.examen_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class InterfazJuego : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz_juego)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewJuego = findViewById<ListView>(R.id.lv_juegos)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatosMemoria.arregloJuego
        )
        listViewJuego.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewJuego)

        val btnAnadirJuego = findViewById<Button>(R.id.btn_crear_juego)
        btnAnadirJuego.setOnClickListener {
            val intentAddJuego = Intent(this, CrearJuego::class.java)
            startActivity(intentAddJuego)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables
            // primitivos
            putInt("idItemSeleccionado",idItemSeleccionado)
            putParcelableArrayList("arregloJuego",BaseDeDatosMemoria.arregloJuego)
            putParcelableArrayList("arregloJuegoPersonaje",BaseDeDatosMemoria.arregloJuegoPersonaje)
            putParcelableArrayList("arregloPersonaje",BaseDeDatosMemoria.arregloPersonaje)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idItemSeleccionado = savedInstanceState.getInt("idItemSeleccionado")
        BaseDeDatosMemoria.arregloJuego = savedInstanceState.getParcelableArrayList<Juego>("arregloJuego")!!
        BaseDeDatosMemoria.arregloJuegoPersonaje= savedInstanceState.getParcelableArrayList<JuegoPersonaje>("arregloJuegoPersonaje")!!
        BaseDeDatosMemoria.arregloPersonaje = savedInstanceState.getParcelableArrayList<Personaje>("arregloPersonaje")!!
        if (idItemSeleccionado == null){
            idItemSeleccionado = 0
        }
        val listViewJuego = findViewById<ListView>(R.id.lv_juegos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatosMemoria.arregloJuego
        )
        listViewJuego.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_juego, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(ActualizarJuego::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarJuego(idItemSeleccionado)
                return true
            }
            R.id.mi_ver_personajes -> {
                Log.i("context-menu", "Personajes: ${idItemSeleccionado}")
                abrirActividadConParametros(InterfazPersonaje::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarJuego = Intent(this, clase)
        intentEditarJuego.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarJuego)
    }

    fun eliminarJuego(
        posicioJuegoEnliminar: Int
    ) {
        val listViewJuego = findViewById<ListView>(R.id.lv_juegos)

        var juegoAEliminar = BaseDeDatosMemoria.arregloJuego.elementAt(posicioJuegoEnliminar)
        var idJuegoAEliminar = juegoAEliminar.idJuego

        var auxListaJuegoPersonaje = arrayListOf<JuegoPersonaje>()

        BaseDeDatosMemoria.arregloJuegoPersonaje.forEachIndexed{ indice: Int, juegoPersonaje: JuegoPersonaje ->
            if(idJuegoAEliminar != juegoPersonaje.idJuego){
                auxListaJuegoPersonaje.add(juegoPersonaje)
            }
        }

        BaseDeDatosMemoria.arregloJuego.removeAt(posicioJuegoEnliminar)
        BaseDeDatosMemoria.arregloJuegoPersonaje = auxListaJuegoPersonaje

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatosMemoria.arregloJuego
        )
        listViewJuego.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}