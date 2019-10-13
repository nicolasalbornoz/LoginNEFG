package com.example.loginnefg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class ListasFragment:Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_listas, container, false)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("Restaurantes")

        recyclerView = view.findViewById(R.id.recycler_Des)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL,false)

        val restaurantes = ArrayList<Restaurante>()

        dbReference.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){

                    for (dataSnapshot1 in dataSnapshot.children){
                        var restaurante = dataSnapshot1.getValue(Restaurante::class.java)
                        restaurantes.add(restaurante!!)
                    }
                    val adapter = RestaurantesAdapter(restaurantes)
                    recyclerView.adapter = adapter
                }
            }

        })

        //val adapter = RestaurantesAdapter(restaurantes)
        //loadDatabase(dbReference)
        //recyclerView.adapter = adapter

        return view

    }

   /* fun loadDatabase(firebaseData: DatabaseReference) {
        val restaurantes = ArrayList<Restaurante>()
        restaurantes.add(Restaurante("Sarku Japan","Japonés","$$$", 2.5F,R.drawable.restaurante))
        restaurantes.add(Restaurante("Res1","Japonés","$", 3.5F,R.drawable.restaurante))
        restaurantes.add(Restaurante("REs2 ","Japonés","$$$", 3.8F,R.drawable.restaurante))
        restaurantes.add(Restaurante("Su","Japonés","$$", 4.5F,R.drawable.restaurante))
        restaurantes.add(Restaurante("Sark Japan","Japonés","$$$", 1.5F,R.drawable.restaurante))

        restaurantes.forEach {
            val key = firebaseData.child("Restaurantes").push().key
            firebaseData.child(key!!).setValue(it)
        }
    }*/
}