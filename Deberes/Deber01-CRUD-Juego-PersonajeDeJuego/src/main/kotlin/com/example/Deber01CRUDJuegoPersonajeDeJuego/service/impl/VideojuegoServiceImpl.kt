package com.example.Deber01CRUDJuegoPersonajeDeJuego.service.impl

import com.example.Deber01CRUDJuegoPersonajeDeJuego.commons.GenericServiceImpl
import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Videojuego
import com.example.Deber01CRUDJuegoPersonajeDeJuego.repository.VideojuegoRepository
import com.example.Deber01CRUDJuegoPersonajeDeJuego.service.api.VideojuegoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class VideojuegoServiceImpl : GenericServiceImpl<Videojuego, Long>(), VideojuegoServiceAPI {
    @Autowired
    lateinit var videojuegoRepository: VideojuegoRepository

    override fun getDao(): CrudRepository<Videojuego, Long> {
        return videojuegoRepository
    }

}