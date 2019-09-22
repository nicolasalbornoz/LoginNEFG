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
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var progressbar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var usuario:String
    private lateinit var correo:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = FirebaseAuth.getInstance()
        progressbar = findViewById(R.id.progressBar2)
        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")

        btRegistrarse.setOnClickListener {
            usuario = etUsuario_Registro.text.toString()
            correo = etCorreo_Registro.text.toString()
            var contraseña = etContraseña_Registro.text.toString()
            var conf_contra = etConfirm_Registro.text.toString()

            if(usuario == ""){
                Toast.makeText(this, "User Name field is empty", Toast.LENGTH_LONG).show()
            }else{
                if(correo == ""){
                    Toast.makeText(this, "Email Address field is empty", Toast.LENGTH_LONG).show()
                }else{
                    if(contraseña== ""){
                        Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG).show()
                    }else{
                        if(contraseña.length > 5 ){
                            if(conf_contra == ""){
                                Toast.makeText(this, "Confirm Password field is empty", Toast.LENGTH_LONG).show()
                            }else{
                                if (contraseña != conf_contra) {
                                    Toast.makeText(this, "Password fields do not match", Toast.LENGTH_LONG).show()
                                    etContraseña_Registro.setText("")
                                    etConfirm_Registro.setText("")
                                }else{
                                    registerUser()
                                }
                            }
                        }else{
                            Toast.makeText(this, "Password must have at least 6 characters", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }

        tvAlready_Account.setOnClickListener {
            goToLogin()

        }

    }

    private fun registerUser(){

        progressbar.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(etCorreo_Registro.text.toString(), etContraseña_Registro.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")

                    val user = auth.currentUser
                    val userDB = dbReference.child(user?.uid.toString())
                    userDB.child("Name").setValue(usuario)
                    userDB.child("Email").setValue(correo)
                    goToLogin()
                } else {
                    progressbar.visibility = View.GONE
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private  fun goToLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
