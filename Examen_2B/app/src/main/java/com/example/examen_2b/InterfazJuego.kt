package com.example.examen_2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InterfazJuego : AppCompatActivity() {
    val db = Firebase.firestore
    val juegos = db.collection("Juegos")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Juego>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz_juego)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarJuegos()

        val btnAnadirJuego = findViewById<Button>(R.id.btn_crear_juego)
        btnAnadirJuego.setOnClickListener {
            val intentAddJuego = Intent(this, CrearJuego::class.java)
            startActivity(intentAddJuego)
        }
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
        var juegoSeleccionado:Juego = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${juegoSeleccionado.idJuego}")
                val abrirActualizarJuego = Intent(this, ActualizarJuego::class.java)
                abrirActualizarJuego.putExtra("posicionJuego", juegoSeleccionado)
                startActivity(abrirActualizarJuego)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                juegos.document("${juegoSeleccionado.idJuego}").delete()
                    .addOnSuccessListener {
                        Log.i("EliminarJuego", "Exito")
                    }
                    .addOnFailureListener {
                        Log.i("EliminarJuego", "Fallido")
                    }
                listarJuegos()
                return true
            }
            R.id.mi_ver_personajes -> {
                Log.i("context-menu", "Personajes: ${idItemSeleccionado}")
                val abrirInicioPersonajes = Intent(this, InterfazPersonaje::class.java)
                abrirInicioPersonajes.putExtra("posicionJuego", juegoSeleccionado)
                startActivity(abrirInicioPersonajes)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listarJuegos(){
        val lv_juegos = findViewById<ListView>(R.id.lv_juegos)
        juegos.get().addOnSuccessListener{ result ->
            var juegosLista = arrayListOf<Juego>()
            for (document in result) {
                juegosLista.add(
                    Juego(
                        document.id.toString(),
                        document.get("fechaLanzamiento").toString(),
                        document.get("aptoTodoPublico").toString(),
                        document.get("nombreJuego").toString(),
                        document.get("horasJuegoHistoria").toString().toInt(),
                        document.get("precio").toString(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                juegosLista
            )
            lv_juegos.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_juegos)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de Juegos fallida")
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarJuego = Intent(this, clase)
        intentEditarJuego.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarJuego)
    }
}