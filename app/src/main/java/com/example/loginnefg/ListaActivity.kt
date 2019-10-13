package com.example.loginnefg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter1: RestaurantesAdapter
    private lateinit var adapter2: RestaurantesAdapter
    private lateinit var adapter3: RestaurantesAdapter
    private lateinit var adapter4: RestaurantesAdapter
    private lateinit var adapter5: RestaurantesAdapter
    private lateinit var adapter6: RestaurantesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val bundle = intent.extras
        val codigo: String = bundle?.getString("cual_presiono").toString()

        //ed_Buscar_restaurante.text = bundle?.getString("cual_presiono")

        recyclerView = findViewById(R.id.recycler_Lista_principal)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        //searchView = findViewById(R.id.)

        val restaurantes1 = ArrayList<Restaurante>()
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
        adapter6 = RestaurantesAdapter(restaurantes6)



        when (codigo) {
            "1" -> recyclerView.adapter = adapter1
            "2" -> recyclerView.adapter = adapter2
            "3" -> recyclerView.adapter = adapter3
            "4" -> recyclerView.adapter = adapter4
            "5" -> recyclerView.adapter = adapter5
            else -> {
                recyclerView.adapter = adapter6
            }
        }

        bt_cerca_De_Mi.setOnClickListener{

            val bundle = Bundle()
            bundle.putDouble("latitud",6.273090)
            bundle.putDouble("longitud",-75.593690)

            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtras(intent)
            startActivity(intent)
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
        adapter1.filter.filter(userInput)
        adapter2.filter.filter(userInput)
        adapter3.filter.filter(userInput)
        adapter4.filter.filter(userInput)
        adapter5.filter.filter(userInput)
        adapter6.filter.filter(userInput)

        return false
    }
}
