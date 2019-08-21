package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var correo_comparar = ""
    private var contraseña_comparar = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogearse.setOnClickListener {
            var correo_Login = etCorreo.text.toString()
            var contraseña_Login = etContraseña.text.toString()
            var datosrecibidos: Bundle? = intent.extras

            if(datosrecibidos !== null){
                correo_comparar = datosrecibidos?.getString("correo")!!
                contraseña_comparar = datosrecibidos?.getString("contraseña")!!
            }else{

            }

            if(correo_Login == ""){
                Toast.makeText(this, "Email Address field is empty", Toast.LENGTH_LONG).show()
            } else{

                if(contraseña_Login == ""){
                    Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG).show()
                } else{

                    if(correo_Login == correo_comparar){

                        if(contraseña_Login == contraseña_comparar){

                            var intent = Intent(this,MainActivity::class.java)
                            intent.putExtra("correo",correo_comparar)
                            intent.putExtra("contraseña",contraseña_comparar)
                            startActivity(intent)
                            finish()

                        } else{
                            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_LONG).show()
                        }

                    } else{
                        Toast.makeText(this, "Email Address is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        tvRegistrarse.setOnClickListener {

            var intent = Intent(this,RegistroActivity::class.java)
            intent.putExtra("correo",correo_comparar)
            intent.putExtra("contraseña",contraseña_comparar)
            startActivityForResult(intent,12)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 12 && resultCode == Activity.RESULT_OK) {

            correo_comparar = data!!.extras!!.getString("correo").toString()
            contraseña_comparar = data!!.extras!!.getString("contraseña").toString()

            Toast.makeText(this, "Resitered successfully", Toast.LENGTH_LONG).show()
            Log.d("name", data!!.extras!!.getString("correo"))
            etCorreo.setText("")
            etContraseña.setText("")

        } else{
            if (requestCode == 12 && resultCode == Activity.RESULT_FIRST_USER) {

                correo_comparar = data!!.extras!!.getString("correo").toString()
                contraseña_comparar = data!!.extras!!.getString("contraseña").toString()
                etCorreo.setText("")
                etContraseña.setText("")
                Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()

            }
        }
    }

}

