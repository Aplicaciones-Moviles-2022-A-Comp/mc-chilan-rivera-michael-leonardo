package com.example.Deber01CRUDJuegoPersonajeDeJuego.service.impl

import com.example.Deber01CRUDJuegoPersonajeDeJuego.commons.GenericServiceImpl
import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Personaje
import com.example.Deber01CRUDJuegoPersonajeDeJuego.repository.PersonajeRepository
import com.example.Deber01CRUDJuegoPersonajeDeJuego.service.api.PersonajeServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class PersonajeServiceImpl : GenericServiceImpl<Personaje, Long>(), PersonajeServiceAPI {
    @Autowired
    lateinit var personajeRepository: PersonajeRepository

    override fun getDao(): CrudRepository<Personaje, Long> {
        return personajeRepository
    }
}