package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import java.sql.DatabaseMetaData

class LoginActivity : AppCompatActivity() {

    //private var correo_comparar = ""
    //private var contraseña_comparar = ""

    private lateinit var progressbar: ProgressBar
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        progressbar = findViewById(R.id.progressBar)

        btLogearse.setOnClickListener {

            var correo_Login = etCorreo.text.toString()
            var contraseña_Login = etContraseña.text.toString()

            if(correo_Login == ""){
                Toast.makeText(this, "Email Address field is empty", Toast.LENGTH_LONG).show()
            } else{

                if(contraseña_Login == ""){
                    Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG).show()
                } else{
                            signInUser()
                }
            }
        }

        tvRegistrarse.setOnClickListener {
            goToRegistroActivity()
        }

    }

    private fun signInUser(){
        progressbar.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(etCorreo.text.toString(), etContraseña.text.toString())

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Logged", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "signInWithEmail:success")
                    goToMainActivity()

                } else {
                    progressbar.visibility = View.GONE
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null)
            goToMainActivity()
    }

    private  fun goToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private  fun goToRegistroActivity(){
        val intent = Intent(this,RegistroActivity::class.java)
        startActivity(intent)
    }

}