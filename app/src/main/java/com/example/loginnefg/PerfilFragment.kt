package com.example.loginnefg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var recyclerView: RecyclerView

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_perfil, container, false)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity!!.applicationContext,gso)

        view.bt_cerrar_sesion.setOnClickListener {
           FirebaseAuth.getInstance().signOut()
           LoginManager.getInstance().logOut()
           mGoogleSignInClient.signOut()
            goToLogin()
        }

        recyclerView = view.findViewById(R.id.recycler_Fav)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)

        val restaurantes = ArrayList<Restaurante>()



        val adapter = RestaurantesAdapter(restaurantes)

        recyclerView.adapter = adapter

        dbReference.addValueEventListener(object: ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    val user = FirebaseAuth.getInstance().currentUser
                    if(user != null){
                        val nombre = dataSnapshot.child(user?.uid.toString()).child("Nombre_Usuario").getValue().toString()
                        val correo = dataSnapshot.child(user?.uid.toString()).child("Email").getValue().toString()
                        val foto = user!!.photoUrl

                        view.tvNombrePerfil.text = nombre
                        view.tvCorreoPerfil.text = correo
                        if(foto != null){
                            Picasso.get().load(foto).into(view.Iv_Perfil)
                        }
                    }else{
                        goToLogin()
                    }
                }
            }

        })

        return view
    }

    private  fun goToLogin(){
        val intent = Intent(activity,LoginActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

}
