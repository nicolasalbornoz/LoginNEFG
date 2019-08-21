package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btRegistrarse.setOnClickListener {
            var usuario = etUsuario_Registro.text.toString()
            var correo = etCorreo_Registro.text.toString()
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
                        if(conf_contra == ""){
                            Toast.makeText(this, "Confirm Password field is empty", Toast.LENGTH_LONG).show()
                        }else{
                            if (contraseña != conf_contra) {
                                Toast.makeText(this, "Password fields do not match", Toast.LENGTH_LONG).show()
                                etContraseña_Registro.setText("")
                                etConfirm_Registro.setText("")
                            }else{

                                var intent = Intent(this,LoginActivity::class.java)
                                intent.putExtra("correo",correo)
                                intent.putExtra("contraseña",contraseña)
                                setResult(Activity.RESULT_OK,intent)
                                finish()
                            }
                        }
                    }
                }
            }
        }

        tvAlready_Account.setOnClickListener {

            var datosrecibidos:Bundle? = intent.extras

            var intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("correo",datosrecibidos?.getString("correo"))
            intent.putExtra("contraseña",datosrecibidos?.getString("contraseña"))
            setResult(Activity.RESULT_FIRST_USER,intent)
            finish()
        }

    }

}
