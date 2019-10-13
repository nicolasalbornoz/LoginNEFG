package com.example.loginnefg

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

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

        /*init{
            itemView.setOnClickListener(this)
        }*/

        fun loadItem(restaurante:Restaurante) {

            val imagen: ImageView = itemView.findViewById(R.id.Iv_ImagenRestaurante)
            val nombre: TextView = itemView.findViewById(R.id.tv_NombreRestaurante)
            val clase: TextView = itemView.findViewById(R.id.tv_ClaseRestaurante)
            val precio: TextView = itemView.findViewById(R.id.tv_PrecioRestaurante)
            val valoracion: TextView = itemView.findViewById(R.id.tv_Puntuacion)

            nombre.text = restaurante.nombre
            clase.text = restaurante.clase
            precio.text = "Precio(" + restaurante.precio + ")"
            valoracion.text = restaurante.valoracion.toString()
            Picasso.get().load(restaurante.imagen!!).into(imagen)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, restaurante.nombre, Toast.LENGTH_LONG).show()
                val intent = Intent(itemView.context,InfoRestauranteActivity::class.java)
                intent.putExtra("imagen",restaurante.imagen!!)
                itemView.context.startActivity(intent)
            }

        }

        /*override fun onClick(p0: View?) {
            val intent = Intent(itemView.context,InfoRestauranteActivity::class.java)
            intent.putExtra("imagen",)
            itemView.context.startActivity(intent)

        }*/
    }

}