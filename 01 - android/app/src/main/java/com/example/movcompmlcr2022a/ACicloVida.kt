package com.example.movcompmlcr2022a

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movcompmlcr2022a.databinding.ActivityAcicloVidaBinding

class ACicloVida : AppCompatActivity() {

    var textoGlobal= ""
    var total = 0

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAcicloVidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val botonContador = findViewById<Button>(R.id.btn_contador)
        //botonContador.text = "0"
        botonContador.setOnClickListener {
            aumentarTotal()
        }
        //mostrarSnackbar("OnCreate")
    }

    fun aumentarTotal() {
        total = total + 1
        val textViewCicloVida = findViewById<TextView>(R.id.txt_contador)
        textViewCicloVida.text = total.toString()
    }

    fun mostrarSnackbar (texto:String) {
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida), textoGlobal, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar( "onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar( "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar( "onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("onPauseonPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar( "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar( "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // GUARDAR LAS VARIABLES
            // SOLO PRIMITIVOS
            putInt("totalGuardado", total)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val totalRecuperado:Int? = savedInstanceState.getInt("totalGuardado")
        if(totalRecuperado != null){
            this.total = totalRecuperado
            val txvCicloVida = findViewById<TextView>(R.id.txt_contador)
            txvCicloVida.text = total.toString()
        }
    }
}