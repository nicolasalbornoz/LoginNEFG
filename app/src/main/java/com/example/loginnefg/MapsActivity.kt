package com.example.loginnefg

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Address

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import java.io.IOException
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var codigo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("Restaurantes")

        val bundle = intent.extras
        codigo = bundle?.getString("codigo").toString()
        Log.d("codigo",codigo)

    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setZoomControlsEnabled(true)

        when (codigo){
            "1" ->{
                Posicion_Restaurantes("Desayuno")
                /*dbReference.child("Desayuno").addValueEventListener(object: ValueEventListener {

                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.exists()){

                            for (dataSnapshot1 in dataSnapshot.children){
                                var mp = dataSnapshot1.getValue(MAPS::class.java)
                                Log.d("mapa", mp!!.lat.toString())
                                var latitud = mp!!.lat!!.toDouble()
                                var longitud = mp!!.long!!.toDouble()
                                var lat_lon:LatLng = LatLng(latitud,longitud)

                               // mMap.addMarker(MarkerOptions().position(lat_lon).title(title_Str))
                            }
                        }
                    }
                })*/
            }
            "2" ->{ Posicion_Restaurantes("Almuerzo")}
            "3" ->{ Posicion_Restaurantes("Cena")}
            "4" ->{ Posicion_Restaurantes("Café")}
            "5" ->{ Posicion_Restaurantes("Postres")}
            "6" ->{ Posicion_Restaurantes("Bar")}
            else ->{
                val bundle = intent.extras
                val lati = bundle?.getDouble("latitud5")
                val longi = bundle?.getDouble("longitud5")
                val Destino = LatLng(lati!!,longi!!)
                placeMarkerOnMap(Destino)
            }
        }
       setUpMap()
    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {

                lastLocation = location
                var currentLatLng = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,12f))

            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng) {

        val markerOptions = MarkerOptions().position(location)

        val titleStr = getAddress(location)  // add these two lines
        markerOptions.title(titleStr)

        mMap.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {

        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses: List<Address>
        val address: Address
        var addressText:String = ""

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

            if (null != addresses && !addresses.isEmpty()) {
                address = addresses.get(0)
                Log.d("direcc",address.toString())
                for (i in 0 downTo address.maxAddressLineIndex step 1) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }

        }catch (e: IOException){
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

    private fun Posicion_Restaurantes(categoria: String){

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {

                lastLocation = location
                var currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))

                dbReference.child(categoria).addValueEventListener(object: ValueEventListener {

                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.exists()){

                            for (dataSnapshot1 in dataSnapshot.children){
                                var mp = dataSnapshot1.getValue(MAPS::class.java)
                                var latitud = mp!!.lat!!.toDouble()
                                var longitud = mp!!.long!!.toDouble()
                                var lat_lon:LatLng = LatLng(latitud,longitud)

                                var resultados = FloatArray(1)
                                Location.distanceBetween(currentLatLng.latitude,currentLatLng.longitude,lat_lon.latitude,lat_lon.longitude,resultados)
                                Log.d("resulti",resultados[0].toString())
                                if (resultados[0] < 600.0){
                                    placeMarkerOnMap(lat_lon)
                                }
                            }
                        }
                    }
                })
            }
        }
    }

}
