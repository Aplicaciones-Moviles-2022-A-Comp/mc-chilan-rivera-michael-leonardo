package com.example.Deber01VideojuegoPersonaje.service.impl

import com.example.Deber01VideojuegoPersonaje.commons.GenericServiceImpl
import com.example.Deber01VideojuegoPersonaje.model.Videojuego
import com.example.Deber01VideojuegoPersonaje.repository.VidejuegoRepository
import com.example.Deber01VideojuegoPersonaje.service.api.VideojuegoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class VideojuegoServiceImpl : GenericServiceImpl<Videojuego, Long>(), VideojuegoServiceAPI {
    @Autowired
    lateinit var  videojuegoRepository: VidejuegoRepository

    override fun getDao(): CrudRepository<Videojuego, Long> {
        return videojuegoRepository
    }
}