package com.example.Deber01VideojuegoPersonaje.commons

import java.io.Serializable

interface GenericServiceAPI<T, ID : Serializable?> { // uso de interfaces genéricas, donde se extenderá de la clase Serializable al ID
    // en esta interfaz van a ir las funciones a utilizar en la aplicación
    fun crearActualizar(entity: T) : T

    fun eliminar(id : ID)

    operator fun get(id : ID): T?

    val obtenerTodo: MutableList<T>?
}