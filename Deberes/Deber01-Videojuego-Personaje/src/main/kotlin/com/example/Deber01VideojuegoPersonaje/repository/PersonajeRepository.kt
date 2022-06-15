package com.example.Deber01VideojuegoPersonaje.repository

import com.example.Deber01VideojuegoPersonaje.model.Personaje
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonajeRepository : CrudRepository<Personaje, Long>{ // extensión de la clase CRUDREPOSITORY, en la cual se pasan los parámetros de Personaje y el tipo de dato de la primary key
}