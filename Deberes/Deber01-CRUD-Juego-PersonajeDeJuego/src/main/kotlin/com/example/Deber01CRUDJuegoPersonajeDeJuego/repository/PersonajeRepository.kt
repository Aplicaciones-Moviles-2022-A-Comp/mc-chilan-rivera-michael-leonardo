package com.example.Deber01CRUDJuegoPersonajeDeJuego.repository

import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Personaje
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface  PersonajeRepository : CrudRepository<Personaje, Long>{
}