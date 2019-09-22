package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var datos_mainA: Bundle? = intent.extras

//        tvMainActivity1.text = datos_mainA?.getString("correo")
//        tvMainActivity2.text = datos_mainA?.getString("contraseña")
        var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        //tvMainActivity1.text = user?.email


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val buscarFragment = BuscarFragment()
        transaction.add(R.id.contenedor,buscarFragment).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.mCerrar_sesion ->{

//                Toast.makeText(this, "Has logged out", Toast.LENGTH_LONG).show()
//                var intent = Intent(this,LoginActivity::class.java)
//                intent.putExtra("correo",tvMainActivity1.text.toString())
//                intent.putExtra("contraseña",tvMainActivity2.text.toString())
//                startActivity(intent)
//                finish()
                FirebaseAuth.getInstance().signOut()
                goToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private  fun goToLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_search -> {
                val buscarFragment = BuscarFragment()
                transaction.replace(R.id.contenedor,buscarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_list -> {
                val listaFragment = ListasFragment()
                transaction.replace(R.id.contenedor,listaFragment).commit()
                return@OnNavigationItemSelectedListener true
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_porfile-> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

}
