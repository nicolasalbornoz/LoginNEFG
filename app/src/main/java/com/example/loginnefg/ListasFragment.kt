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
    private lateinit var adapter: RestaurantesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_listas, container, false)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("Restaurantes")

        recyclerView = view.findViewById(R.id.recycler_Des)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL,false)


        dbReference.child("Desayuno").addValueEventListener(object: ValueEventListener{
            val restaurantes1 = ArrayList<Restaurante>()

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){

                    for (dataSnapshot1 in dataSnapshot.children){
                        var restauranteD = dataSnapshot1.getValue(Restaurante::class.java)
                        restaurantes1.add(restauranteD!!)
                    }
                    adapter = RestaurantesAdapter(restaurantes1)
                    recyclerView.adapter = adapter
                }
            }

        })

        //loadDatabase(dbReference)

        return view

    }

   /*fun loadDatabase(firebaseData: DatabaseReference) {
        val restaurantes = ArrayList<Restaurante>()
        restaurantes.add(Restaurante("Sarku Japan","Japonés","$$$", 7.5F,R.drawable.restaurante,"Carrera 50 #3-50","L-V 8am-10pm",6.266472,-75.594330))
        restaurantes.add(Restaurante("Crepes & Waffers","Crepes","$$$", 8.5F,R.drawable.restaurante,"Carrera 45 #32-12","L-V 8am-10pm",6.267251,-75.594531))
        restaurantes.add(Restaurante("Frijoles","Colombiano","$$", 3.8F,R.drawable.restaurante,"Carrera 50 #33-33","-V 8am-10pm",6.264094,-75.593719))

        restaurantes.forEach {
            val key = firebaseData.child("Restaurantes").push().key
            firebaseData.child("Desayuno").child(key!!).setValue(it)
        }
    }*/
}