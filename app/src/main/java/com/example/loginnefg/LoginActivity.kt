package com.example.loginnefg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import java.sql.DatabaseMetaData

import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var progressbar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth.AuthStateListener

    private lateinit var loginButton: LoginButton
    private lateinit var callbackManager: CallbackManager

    private var RC_SIGN_IN:Int = 1234
    private lateinit var googleSignInClient:GoogleSignInClient

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        progressbar = findViewById(R.id.progressBar)

        loginButton = findViewById(R.id.login_button)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")

        callbackManager = CallbackManager.Factory.create()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)


        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }
        })

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

        sign_in_button.setOnClickListener {
            signIn()
        }

    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val userDB = dbReference.child(user?.uid.toString())
                    userDB.child("Nombre_Usuario").setValue(user?.displayName)
                    userDB.child("Email").setValue(user?.email)
                    goToMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userDB = dbReference.child(user?.uid.toString())
                    userDB.child("Nombre_Usuario").setValue(user?.displayName)
                    userDB.child("Email").setValue(user?.email)
                    goToMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

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
        /*val bundle = Bundle()
        val currentUser = auth.currentUser

        val nombre = currentUser!!.displayName
        val correo = currentUser!!.email
        val foto = currentUser!!.photoUrl

        bundle.putString("Nombre_usuarioFG",nombre)
        bundle.putString("Correo_usuarioFG",correo)

        intent.putExtras(bundle)*/
        startActivity(intent)
        finish()
    }

    private  fun goToRegistroActivity(){
        val intent = Intent(this,RegistroActivity::class.java)
        startActivity(intent)
    }

}