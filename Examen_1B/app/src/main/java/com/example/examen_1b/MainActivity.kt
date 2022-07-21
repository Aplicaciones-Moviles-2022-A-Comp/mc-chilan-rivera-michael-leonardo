package com.example.examen_1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)

        val botonVerJuegos = findViewById<Button>(R.id.btn_ver_juegos)
        botonVerJuegos.setOnClickListener {
            val intent = Intent(this, InterfazJuego::class.java)
            startActivity(intent)
        }
    }
}