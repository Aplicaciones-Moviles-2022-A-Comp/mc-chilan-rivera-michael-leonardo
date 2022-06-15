package com.example.Deber01VideojuegoPersonaje.repository

import com.example.Deber01VideojuegoPersonaje.model.Videojuego
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VidejuegoRepository : CrudRepository<Videojuego, Long> { // extensión de la clase CRUDREPOSITORY, en la cual se pasan los parámetros de Videojuego y el tipo de dato de la primary key
}