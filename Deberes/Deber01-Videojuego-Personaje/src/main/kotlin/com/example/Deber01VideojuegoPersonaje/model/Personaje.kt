package com.example.Deber01VideojuegoPersonaje.model

import java.sql.Date
import javax.persistence.*

@Entity(name = "Personaje")
data class Personaje ( //creación de la entidad Personaje que tendrá los mismos campos que en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_personaje: Long = 0,
    @Column
    val id_videojuego: Long = 0,
    @Column
    val fecha_nacimiento: Date = Date(0, 0, 0),
    @Column
    val personaje_principal: Boolean = false,
    @Column
    val nombre: String = "",
    @Column
    val edad_inicial: Int = 0,
    @Column
    val altura: Double = 0.0){}