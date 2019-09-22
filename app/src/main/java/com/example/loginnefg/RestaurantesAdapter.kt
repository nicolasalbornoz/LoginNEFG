package com.example.loginnefg

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cart.view.*

class RestaurantesAdapter: RecyclerView.Adapter<RestaurantesAdapter.RestaurantesViewHolder>{

    private var listaRestaurantes: MutableList<Info>? = null
    private var context: Context? = null

    constructor(ok:String,context: Context){
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantesViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.cart,parent,false)
        return RestaurantesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RestaurantesViewHolder, position: Int) {
        var peluchitos = listaRestaurantes!![position]
        holder.loadItem(peluchitos)
    }

    class RestaurantesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun loadItem(Restaurante:Info) {
            itemView.tvRestaurante_stock.text = "Restaurante1"
        }
    }

}