package com.example.Deber01CRUDJuegoPersonajeDeJuego.model

import javax.persistence.*

@Entity(name = "Videojuego")
data class Videojuego (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_videojuego: Long = 0,
    @Column
    val nombre: String = "",
    @Column
    val casa_desarrolladora: String = "",
    @Column
    val clasificacion: String = "",
    @Column
    val tipo_consola: String = ""){}