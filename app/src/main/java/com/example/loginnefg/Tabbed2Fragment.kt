package com.example.loginnefg


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tabbed2.view.*

class Tabbed2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_tabbed2, container, false)

        var direccion = arguments!!.getString("direccion1")
        var horario = arguments!!.getString("horarios1")
        var latitud = arguments!!.getDouble("latitud1")
        var longitud = arguments!!.getDouble("longitud1")

        val bundle = Bundle()
        bundle.putDouble("latitud5",latitud)
        bundle.putDouble("longitud5",longitud)

        view.tv_direccion_prestaña_info.text = direccion
        view.tv_horario_prestaña_info.text = horario
        view.imagen_googleMaps.setOnClickListener{

            val intent = Intent(activity,MapsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        return view
    }


}
