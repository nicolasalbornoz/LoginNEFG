package com.example.loginnefg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {

   // private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

        var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser*/

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val buscarFragment = BuscarFragment()
        transaction.add(R.id.contenedor,buscarFragment).commit()

       /* val bundle = intent.extras
        val nombre: String = bundle?.getString("Nombre_usuarioFG").toString()
        val correo: String = bundle?.getString("Correo_usuarioFG").toString()

        val args = Bundle()
        args.putString("corrio",correo)
        args.putString("nombri",nombre)

        val newFragment = PerfilFragment()
        newFragment.setArguments(args)*/

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.mCerrar_sesion ->{

                FirebaseAuth.getInstance().signOut()
                LoginManager.getInstance().logOut()
                mGoogleSignInClient.signOut()
                goToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private  fun goToLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }*/

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
            }

            R.id.navigation_porfile-> {
                val perfilFragment = PerfilFragment()
                transaction.replace(R.id.contenedor,perfilFragment).commit()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
}

/*try {
     val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
     for (signature in info.signatures) {
         val md = MessageDigest.getInstance("SHA")
         md.update(signature.toByteArray())
         val hashKey = String(Base64.encode(md.digest(), 0))
         Log.e("AppLog", "key:$hashKey=")
     }
 } catch (e: Exception) {
     Log.e("AppLog", "error:", e)
 }*/