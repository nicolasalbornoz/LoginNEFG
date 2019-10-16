package com.example.loginnefg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantesAdapter


    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val bundle = intent.extras
        val codigo: String = bundle?.getString("cual_presiono").toString()

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("Restaurantes")

        //ed_Buscar_restaurante.text = bundle?.getString("cual_presiono")

        recyclerView = findViewById(R.id.recycler_Lista_principal)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        //searchView = findViewById(R.id.)

        /*val restaurantes1 = ArrayList<Restaurante>()
        restaurantes1.add(Restaurante("Sarku ","Japonés","$$$", 2.5F,R.drawable.restaurante))
        restaurantes1.add(Restaurante("Res1","Japonés","$", 3.5F,R.drawable.star))

        val restaurantes2 = ArrayList<Restaurante>()
        restaurantes2.add(Restaurante("REs2 ","Japonés","$$$$$", 3.8F,R.drawable.restaurante))
        restaurantes2.add(Restaurante("Su","Japonés","$$", 4.5F,R.drawable.restaurante))
        restaurantes2.add(Restaurante("Crepes","variado","$$$", 1.5F,R.drawable.restaurante))

        val restaurantes3 = ArrayList<Restaurante>()
        restaurantes3.add(Restaurante("Perritos ","Rapida","$", 3.8F,R.drawable.restaurante))

        val restaurantes4 = ArrayList<Restaurante>()
        restaurantes4.add(Restaurante("JapanJapan","Japonés","$$", 2.5F,R.drawable.restaurante))
        restaurantes4.add(Restaurante("Rescua","Japonés","$", 3.5F,R.drawable.restaurante))
        restaurantes4.add(Restaurante("La burgueseria","Japonés","$$$", 1.5F,R.drawable.restaurante))

        val restaurantes5 = ArrayList<Restaurante>()
        restaurantes5.add(Restaurante("A lo paisa","Colombiana","$$$", 3.8F,R.drawable.restaurante))
        restaurantes5.add(Restaurante("Sushi shushi","Japonés","$$", 4.5F,R.drawable.restaurante))
        restaurantes5.add(Restaurante("Mister","Pollo","$", 1.5F,R.drawable.restaurante))

        val restaurantes6 = ArrayList<Restaurante>()
        restaurantes6.add(Restaurante("Pana","Colombiana","$$$$", 2.5F,R.drawable.restaurante))
        restaurantes6.add(Restaurante("Salsa","Japonés","$$$$", 1.5F,R.drawable.restaurante))

        adapter1 = RestaurantesAdapter(restaurantes1)
        adapter2 = RestaurantesAdapter(restaurantes2)
        adapter3 = RestaurantesAdapter(restaurantes3)
        adapter4 = RestaurantesAdapter(restaurantes4)
        adapter5 = RestaurantesAdapter(restaurantes5)
        adapter6 = RestaurantesAdapter(restaurantes6)*/

        /*val restaurantes1 = ArrayList<Restaurante>()
        restaurantes1.add(Restaurante("Sarku ","Japonés","$$$", 2.5F,R.drawable.restaurante,"Carrera 50 #3-05","L-V 8-10",6.273090,-75.593690))
        restaurantes1.add(Restaurante("Res1","Japonés","$", 3.5F,R.drawable.star,"Carrera 78A # 4- 23","L-V 6-9",6.273090,-75.57690))

        adapter1 = RestaurantesAdapter(restaurantes1)*/

        when (codigo) {
            "1" -> {

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
            }
            "2" -> {
                dbReference.child("Almuerzo").addValueEventListener(object: ValueEventListener{
                val restaurantes2 = ArrayList<Restaurante>()

                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.exists()){

                            for (dataSnapshot1 in dataSnapshot.children){
                                var restauranteA = dataSnapshot1.getValue(Restaurante::class.java)
                                restaurantes2.add(restauranteA!!)
                            }
                             adapter = RestaurantesAdapter(restaurantes2)
                            recyclerView.adapter = adapter
                        }
                    }

                })
            }
            "3" -> {
                dbReference.child("Cena").addValueEventListener(object: ValueEventListener{
                val restaurantes = ArrayList<Restaurante>()

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()){

                        for (dataSnapshot1 in dataSnapshot.children){
                            var restauranteC = dataSnapshot1.getValue(Restaurante::class.java)
                            restaurantes.add(restauranteC!!)
                        }
                        adapter = RestaurantesAdapter(restaurantes)
                        recyclerView.adapter = adapter
                    }
                }

            })
            }
            "4" -> {
                dbReference.child("Café").addValueEventListener(object: ValueEventListener{
                val restaurantes = ArrayList<Restaurante>()

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()){

                        for (dataSnapshot1 in dataSnapshot.children){
                            var restauranteCafe = dataSnapshot1.getValue(Restaurante::class.java)
                            restaurantes.add(restauranteCafe!!)
                        }
                        adapter = RestaurantesAdapter(restaurantes)
                        recyclerView.adapter = adapter
                    }
                }

            })}
            "5" -> {
                dbReference.child("Postres").addValueEventListener(object: ValueEventListener{
                val restaurantes = ArrayList<Restaurante>()

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()){

                        for (dataSnapshot1 in dataSnapshot.children){
                            var restauranteP = dataSnapshot1.getValue(Restaurante::class.java)
                            restaurantes.add(restauranteP!!)
                        }
                        adapter = RestaurantesAdapter(restaurantes)
                        recyclerView.adapter = adapter
                    }
                }

            })}
            "6" -> {
                dbReference.child("Bar").addValueEventListener(object: ValueEventListener{
                    val restaurantes = ArrayList<Restaurante>()

                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.exists()){

                            for (dataSnapshot1 in dataSnapshot.children){
                                var restauranteBAR = dataSnapshot1.getValue(Restaurante::class.java)
                                restaurantes.add(restauranteBAR!!)
                            }
                            adapter = RestaurantesAdapter(restaurantes)
                            recyclerView.adapter = adapter
                        }
                    }

                })
            }
            else -> {
                null
            }
        }

        bt_cerca_De_Mi.setOnClickListener{

            when (codigo){
                "1" ->{
                    val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)
                }
                "2" ->{val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)}
                "3" ->{
                    val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)
                }
                "4" ->{
                    val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)
                }
                "5" ->{
                    val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)
                }
                "6" ->{
                    val bundle = Bundle()
                    bundle.putString("codigo",codigo)
                    val intent = Intent(this,MapsActivity::class.java)
                    intent.putExtras(bundle)
                    Log.d("codigo",codigo)
                    startActivity(intent)
                }
                else ->{null}
            }

            //val bundle = Bundle()
            //bundle.putDouble("latitud",6.273090)
            //bundle.putDouble("longitud",-75.593690)

           // val intent = Intent(this,MapsActivity::class.java)
           // intent.putExtras(intent)
            //startActivity(intent)
        }

        bt_filtros.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu,menu)
        var menuItem = menu!!.findItem(R.id.action_search)
        val searchView = menuItem.getActionView() as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        val userInput = p0!!.toLowerCase()
        adapter.filter.filter(userInput)

        return false
    }


}
