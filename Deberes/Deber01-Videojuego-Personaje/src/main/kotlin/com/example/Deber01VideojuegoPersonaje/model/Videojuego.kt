package com.example.Deber01VideojuegoPersonaje.model

import java.sql.Date
import javax.persistence.*

@Entity(name = "Videojuego")
data class Videojuego ( //creación de la entidad Videojuego que tendrá los mismos campos que en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_videojuego: Long = 0,
    @Column
    val fecha_lanzamiento: Date = Date(0, 0, 0),
    @Column
    val apto_todo_publico: Boolean = false,
    @Column
    val nombre: String = "",
    @Column
    val horas_juego_historia: Int = 0,
    @Column
    val precio: Double = 0.0){}