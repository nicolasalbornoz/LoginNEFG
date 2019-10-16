package com.example.loginnefg

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.loginnefg.ui.main.SectionsPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tabbed1.*

class InfoRestauranteActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_restaurante)

        val bundle = intent.extras
        val imagen = bundle?.getInt("imagen")
        val nombre = bundle?.getString("nombre")
        val clase = bundle?.getString("clase")
        val precio = bundle?.getString("precio")
        val valoracion = bundle?.getFloat("valoracion")
        val direccion = bundle?.getString("direccion")
        val horarios = bundle?.getString("horarios")
        val latitud = bundle?.getDouble("latitud")
        val longitud = bundle?.getDouble("longitud")

        var Imagen_info:ImageView = findViewById(R.id.Iv_FotoInfoRestaurante)
        var Nombre_info: TextView = findViewById(R.id.Tv_NombreInfoRestaurante)
        var Categoria_precio_info: TextView = findViewById(R.id.Tv_Cate_precio_InfoRestaurante)

        Nombre_info.text = nombre
        Categoria_precio_info.text = "Categoría: " + clase + " | Precio: " + precio
        Picasso.get().load(imagen!!).into(Imagen_info)

        Log.d("tabbed",direccion)
//        section_label.text = direccion


        val args = Bundle()
        args.putFloat("valoracion1", valoracion!!)
        args.putString("direccion1",direccion)
        args.putString("horarios1",horarios)
        args.putDouble("longitud1",longitud!!)
        args.putDouble("latitud1",latitud!!)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,args)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}