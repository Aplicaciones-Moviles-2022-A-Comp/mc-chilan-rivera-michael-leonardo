package com.example.Deber01CRUDJuegoPersonajeDeJuego.model

import javax.persistence.*

@Entity(name = "Personaje")
data class Personaje (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_personaje: Long = 0,
    @Column
    val id_videojuego: Long = 0,
    @Column
    val nombre: String = "",
    @Column
    val genero: String = "",
    @Column
    val tipo_personaje: String = "",
    @Column
    val altura: String = ""){}