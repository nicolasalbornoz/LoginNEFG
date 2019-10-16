package com.example.loginnefg

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class RestaurantesAdapter (var list: ArrayList<Restaurante>): RecyclerView.Adapter<RestaurantesAdapter.RestaurantesViewHolder>(), Filterable {

    private var listaRestaurantesFULL: ArrayList<Restaurante> = ArrayList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantesAdapter.RestaurantesViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item,parent,false)
        return RestaurantesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RestaurantesViewHolder, position: Int) {
        holder.loadItem(list[position])

    }

    override fun getFilter(): Filter {

        return object : Filter(){

            override fun performFiltering(charString: CharSequence?): FilterResults {

                val charSearch = charString.toString()
                var FilteredList: ArrayList<Restaurante> = ArrayList()

                if(charSearch.isEmpty()){

                    FilteredList.addAll(listaRestaurantesFULL)

                }else{
                    for(row: Restaurante in listaRestaurantesFULL){
                        if(row.nombre!!.toLowerCase().contains(charSearch.toLowerCase())){
                            FilteredList.add(row)
                        }
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = FilteredList
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                list.clear()
                list = filterResults!!.values as ArrayList<Restaurante>
                notifyDataSetChanged()
            }

        }
    }

    class RestaurantesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        private var like:Boolean = true
        private lateinit var dbReference: DatabaseReference
        private lateinit var database: FirebaseDatabase
        val user = FirebaseAuth.getInstance().currentUser
        private var ya_esta:Boolean = false

        fun loadItem(restaurante:Restaurante) {

            val imagen: ImageView = itemView.findViewById(R.id.Iv_ImagenRestaurante)
            val nombre: TextView = itemView.findViewById(R.id.tv_NombreRestaurante)
            val clase: TextView = itemView.findViewById(R.id.tv_ClaseRestaurante)
            val precio: TextView = itemView.findViewById(R.id.tv_PrecioRestaurante)
            val valoracion: TextView = itemView.findViewById(R.id.tv_Puntuacion)
            val Favorito: ImageView = itemView.findViewById(R.id.Iv_favorites)

            database = FirebaseDatabase.getInstance()
            dbReference = database.reference.child("User").child(user?.uid.toString()).child("Favoritos")

            nombre.text = restaurante.nombre
            clase.text = restaurante.clase
            precio.text = "Precio(" + restaurante.precio + ")"
            valoracion.text = restaurante.valoracion.toString()
            Picasso.get().load(restaurante.imagen!!).into(imagen)

            itemView.setOnClickListener{
               // Toast.makeText(itemView.context, restaurante.nombre, Toast.LENGTH_LONG).show()
                val intent = Intent(itemView.context,InfoRestauranteActivity::class.java)
                intent.putExtra("imagen",restaurante.imagen!!)
                intent.putExtra("nombre",restaurante.nombre!!)
                intent.putExtra("clase",restaurante.clase!!)
                intent.putExtra("precio",restaurante.precio!!)
                intent.putExtra("valoracion",restaurante.valoracion!!)
                intent.putExtra("direccion",restaurante.direccion!!)
                intent.putExtra("horarios",restaurante.horarios!!)
                intent.putExtra("latitud",restaurante.lat!!)
                intent.putExtra("longitud",restaurante.long!!)
                itemView.context.startActivity(intent)
            }

            Favorito.setOnClickListener {

                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Agregar restaurante a favoritos?")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        dbReference.addValueEventListener(object: ValueEventListener {

                            override fun onCancelled(p0: DatabaseError) {
                            }

                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                    for (dataSnapshot1 in dataSnapshot.children){
                                        var restauranteF = dataSnapshot1.getValue(Restaurante::class.java)
                                        if(restauranteF!!.nombre == restaurante.nombre){
                                            ya_esta = true
                                        }
                                    }

                                    if(ya_esta == true){
                                        Toast.makeText(itemView.context, "Restaurante en favoritos", Toast.LENGTH_LONG).show()
                                    }else{
                                        val restauranteF = ArrayList<Restaurante>()
                                        restauranteF.add(restaurante)
                                        restauranteF.forEach {
                                            val key = dbReference.push().key
                                            dbReference.child(key!!).setValue(it)
                                            Toast.makeText(itemView.context, "Restaurante agregado a favoritos", Toast.LENGTH_LONG).show()
                                        }
                                    }
                            }
                        })
                    }
                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    }
                    builder.show()
            }

        }

    }
}