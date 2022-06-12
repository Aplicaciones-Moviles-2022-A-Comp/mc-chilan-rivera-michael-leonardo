package com.example.Deber01CRUDJuegoPersonajeDeJuego.repository

import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Videojuego
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VideojuegoRepository : CrudRepository<Videojuego, Long> {
}