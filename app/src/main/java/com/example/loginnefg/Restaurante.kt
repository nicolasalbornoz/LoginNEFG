package com.example.loginnefg

class Restaurante {

    var nombre: String? = null
    var clase:  String? = null
    var precio: String? = null
    var valoracion: Float? = null
    var imagen: Int? = null

    constructor(){

    }
    constructor(nombre: String?, clase: String?, precio: String?, valoracion: Float?, imagen: Int?) {
        this.nombre = nombre
        this.clase = clase
        this.precio = precio
        this.valoracion = valoracion
        this.imagen = imagen
    }
}