package com.example.movcompmlcr2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {

    val arreglo: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAñadirListView = findViewById<Button>(
            R.id.btn_añadir_list_view)

        botonAñadirListView.setOnClickListener {
            añadirNumero(adaptador)
        }
    }

    fun añadirNumero(
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(1)
        adaptador.notifyDataSetChanged()
    }
}