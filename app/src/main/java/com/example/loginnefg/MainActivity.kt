package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var datos_mainA: Bundle? = intent.extras

        tvMainActivity1.text = datos_mainA?.getString("correo")
        tvMainActivity2.text = datos_mainA?.getString("contraseña")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.mCerrar_sesion ->{

                Toast.makeText(this, "Has logged out", Toast.LENGTH_LONG).show()
                var intent = Intent(this,LoginActivity::class.java)
                intent.putExtra("correo",tvMainActivity1.text.toString())
                intent.putExtra("contraseña",tvMainActivity2.text.toString())
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
