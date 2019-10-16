package com.example.loginnefg


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.collection.LLRBNode
import kotlinx.android.synthetic.main.fragment_tabbed1.view.*

class Tabbed1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_tabbed1, container, false)

        var valoracion = arguments!!.getFloat("valoracion1")

        Log.d("hoe",valoracion.toString())

        view.tv_valoracion_prestaña_destacado.text = "Calificado " + valoracion + " de 10.0"


        var pieChart: PieChart = view.findViewById(R.id.grafica)

        pieChart.holeRadius = 10F
        pieChart.transparentCircleRadius = 12F

        val value = listOf<PieEntry>(PieEntry(valoracion*10,"Valoración"),PieEntry((100.0F - valoracion*10),""))

        val pieDataSet: PieDataSet = PieDataSet(value,"")

        pieDataSet.sliceSpace = 2F
        pieDataSet.valueFormatter = PercentFormatter()
        pieDataSet.valueTextSize = 10F
        val pieData: PieData = PieData(pieDataSet)
        pieChart.data = pieData

        return view

    }


}
