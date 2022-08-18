package com.example.movcompmlcr2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IFirebaseFirestore : AppCompatActivity() {

    var arreglo: ArrayList<ICitiesDto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        setContentView(R.layout.activity_ifirebase_firestore)
        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        botonOrderBy.setOnClickListener {
            citiesRefUnico
                .orderBy("population") // NOS USAMOS CON DOCUENT xq en DOCUMENT nos devuelve 1
                .get()
                .addOnSuccessListener {
                    arreglo = arrayListOf()
                    for (ciudad in it){
                        anadirArregloCiudad(arreglo, ciudad)
                    }
                    adaptador.notifyDataSetChanged()
                }
        }
        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener {
            citiesRefUnico
                .document("BJ")
                .get()
                .addOnSuccessListener {
                    arreglo = arrayListOf()
                    arreglo.add(
                        ICitiesDto(
                            it.get("name") as String?, it.get("state") as String?,
                            it.get("country") as String?, it.get("capital") as Boolean?,
                            it.get("population") as Integer?, it.get("regions") as ArrayList<String>
                        )
                    )
                    adaptador.notifyDataSetChanged()
                }
        }
    }

    fun anadirArregloCiudad(
        arregloNuevo: ArrayList<ICitiesDto>,
        ciudad: QueryDocumentSnapshot
    ){
        arregloNuevo.add(
            ICitiesDto(
                ciudad.get("name") as String?, ciudad.get("state") as String?,
                ciudad.get("country") as String?, ciudad.get("capital") as Boolean?,
                ciudad.get("population") as Integer?, ciudad.get("regions") as ArrayList<String>
            )
        )
    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }
}