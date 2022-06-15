package com.example.Deber01VideojuegoPersonaje.service.impl

import com.example.Deber01VideojuegoPersonaje.commons.GenericServiceImpl
import com.example.Deber01VideojuegoPersonaje.model.Personaje
import com.example.Deber01VideojuegoPersonaje.repository.PersonajeRepository
import com.example.Deber01VideojuegoPersonaje.service.api.PersonajeServiceAPI
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