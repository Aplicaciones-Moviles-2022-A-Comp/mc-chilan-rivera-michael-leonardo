package com.example.movcompmlcr2022a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            if(result.data != null) {
                val data = result.data
                Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                Log.i("intent-epn", "${data?.getStringExtra("apellidoModificado")}")
                Log.i("intent-epn", "${data?.getIntExtra("edadModificado", 0)}")
            }
        }
    }
    val contenidoIntentImplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            if(result.data != null) {
                val uri: Uri = result.data!!.data!!
                val cursor = contentResolver.query(
                    uri,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                cursor?.moveToFirst()
                val indiceTelefono = cursor?.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
                val telefono = cursor?.getString(
                    indiceTelefono!!
                )
                cursor?.close()
                Log.i("intent-epn", "Telefono ${telefono}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Base de datos sqlite
        EBaseDeDatos.TablaEntrenador = ESqliteHelperEntrenador(this)
        //Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)

        val botonCicloVida = findViewById<Button>/*se castea indicando que es un tipo Button*/(R/*indica la carpeta recursos*/.id/*muestra todos los ids*/.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java)
        }

        val botonContador = findViewById<Button>(R.id.btn_ir_contador)
        botonContador.setOnClickListener {
            irActividad(Contador::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            contenidoIntentImplicito.launch(intentConRespuesta)
            //ActivityResultContracts.StartActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite.setOnClickListener {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRecyclerView = findViewById<Button>(R.id.btn_recycler_view)
        botonRecyclerView.setOnClickListener {
            irActividad(GRecyclerView::class.java)
        }

        val botonIrFirebase = findViewById<Button>(R.id.btn_ir_firebase)
        botonIrFirebase.setOnClickListener {
            irActividad(HFirebaseUIAuth::class.java)
        }

        val botonIrFirestore = findViewById<Button>(R.id.btn_ir_firestore)
        botonIrFirestore.setOnClickListener {
            irActividad(IFirebaseFirestore::class.java)
        }

        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps.setOnClickListener {
            irActividad(FGoogleMaps::class.java)
        }
    }

    fun irActividad (
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        //startActivity(intent) // esta función se encuentra dentro de la clase de la cual se esta heredando (en este caso "AppCompatActivity()")
        // Enviar parámetros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Michael")
        intentExplicito.putExtra("apellido", "Chilán")
        intentExplicito.putExtra("edad", 25)
        intentExplicito.putExtra("entrenadorPrincipal", BEntrenador(1, "Michael", "Paleta"))

        contenidoIntentExplicito.launch(intentExplicito)
        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO) // 401
    }
}