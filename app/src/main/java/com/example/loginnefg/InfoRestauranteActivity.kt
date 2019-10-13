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
import com.example.loginnefg.ui.main.SectionsPagerAdapter
import com.squareup.picasso.Picasso

class InfoRestauranteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_restaurante)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val bundle = intent.extras
        val imagen = bundle?.getInt("imagen")!!

        var Imagen_info:ImageView = findViewById(R.id.Iv_FotoInfoRestaurante)

        Picasso.get().load(imagen).into(Imagen_info)

        Log.d("Holi",imagen.toString())

    }
}