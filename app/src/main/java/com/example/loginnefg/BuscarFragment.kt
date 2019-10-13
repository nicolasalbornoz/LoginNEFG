package com.example.loginnefg


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_buscar.view.*

class BuscarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_buscar, container, false)

        view.opcion1.setOnClickListener {
           onClick("1")
        }
        view.opcion2.setOnClickListener {
            onClick("2")
        }
        view.opcion3.setOnClickListener {
            onClick("3")
        }
        view.opcion4.setOnClickListener {
            onClick("4")
        }
        view.opcion5.setOnClickListener {
            onClick("5")
        }
        view.opcion6.setOnClickListener {
            onClick("6")
        }

        return view
    }

    fun onClick(codigo: String){

        val bundle = Bundle()

        bundle.putString("cual_presiono",codigo)

        val intent = Intent(activity,ListaActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
