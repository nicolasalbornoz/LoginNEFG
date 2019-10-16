package com.example.loginnefg

class Restaurante {

    var nombre: String? = null
    var clase:  String? = null
    var precio: String? = null
    var valoracion: Float? = null
    var imagen: Int? = null
    var direccion: String? = null
    var lat: Double? = null
    var long: Double? = null
    var horarios: String? = null

    constructor(){

    }
    constructor(nombre: String?, clase: String?, precio: String?, valoracion: Float?, imagen: Int?,direccion: String?,horarios: String?, latitud: Double?, longitud: Double?) {
        this.nombre = nombre
        this.clase = clase
        this.precio = precio
        this.valoracion = valoracion
        this.imagen = imagen
        this.direccion = direccion
        this.horarios = horarios
        this.lat = latitud
        this.long = longitud
    }
}