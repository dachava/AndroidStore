package com.tiendavirtual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tiendavirtual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        setContentView(R.layout.activity_main)

        binding.btLogin.setOnClickListener {
            haceLogin();
        }
        binding.btRegister.setOnClickListener {
                haceRegister();

            }
        }

        private fun haceLogin() {
            TODO("Not yet implemented")
        }
    }

    private fun haceRegister() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){ task ->}
        if(task,isSuccessful){
            Log.d("Creando usuario", "Registrado")
            val user = auth.currentUser
            actualiza(user)
        }else{
            Log.d("Creando usuario", "Fallo")
            Toast.makeText(baseContext,"Fallo",Toast.LENGTH_LONG).show()
            actualiza(user)
        }*/
        }
    }

