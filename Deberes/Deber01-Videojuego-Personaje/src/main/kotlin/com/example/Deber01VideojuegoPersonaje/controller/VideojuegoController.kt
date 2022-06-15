package com.example.Deber01VideojuegoPersonaje.controller

import com.example.Deber01VideojuegoPersonaje.model.Videojuego
import com.example.Deber01VideojuegoPersonaje.service.api.VideojuegoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/videojuego")
@CrossOrigin("*")
class VideojuegoController {
    @Autowired
    lateinit var videojuegoServiceAPI: VideojuegoServiceAPI

    @GetMapping("/lista_de_juegos")
    fun obtenerVideojuegos() : MutableList<Videojuego>? {
        return videojuegoServiceAPI.obtenerTodo
    }

    @PostMapping("/crearActualizar")
    fun crearActualizarVideojuego(@RequestBody videojuego: Videojuego) : ResponseEntity<Videojuego> {
        var obj = videojuegoServiceAPI.crearActualizar(videojuego)
        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarVideojuego(@PathVariable id: Long) : ResponseEntity<Videojuego> {
        val videojuego = videojuegoServiceAPI.get(id)
        if (videojuego != null) {
            videojuegoServiceAPI.eliminar(id)
        } else {
            return ResponseEntity<Videojuego>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }
}