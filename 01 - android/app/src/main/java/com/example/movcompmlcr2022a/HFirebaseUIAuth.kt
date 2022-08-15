package com.example.movcompmlcr2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HFirebaseUIAuth : AppCompatActivity() {
    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode === RESULT_OK){
            if(res.idpResponse != null){
                this.seLogeo(res.idpResponse!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        btnLogin.setOnClickListener {
            // Choose authentication providers
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
            // Create and launch sign-in intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    seDeslogueo()
                }
        }
    }

    fun seLogeo(res: IdpResponse){
        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogin.visibility = View.INVISIBLE
        btnLogout.visibility = View.VISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }
    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLogueado = FirebaseAuth.getInstance().currentUser
        if(usuario.email != null && usuarioLogueado != null){
            val db = Firebase.firestore //obtenemos referencia
            val roles = arrayListOf("usuario") // creamos el arreglo de permisos
            val email = usuarioLogueado.email // correo
            val uid = usuarioLogueado.uid //identificador
            val nuevoUsuario = hashMapOf<String, Any>( // { roles:... uid:...}
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )
            db.collection("usuario")
                // .document() // Me crea el identificador el Firestore
                .document(email.toString()) // El identificador lo setea ya
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    // Ya se crea el usuario en Firestore
                }
                .addOnFailureListener{
                    // Hubo errores creando usuario en Firestore
                }
        }
    }
    fun seDeslogueo(){
        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogin.visibility = View.VISIBLE
        btnLogout.visibility = View.INVISIBLE
    }
}