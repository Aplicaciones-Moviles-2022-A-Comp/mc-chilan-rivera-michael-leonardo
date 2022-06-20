package com.example.Deber01VideojuegoPersonaje.controller

import com.example.Deber01VideojuegoPersonaje.model.Videojuego
import com.example.Deber01VideojuegoPersonaje.service.api.VideojuegoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        videojuegoServiceAPI.guardar("videojuegos.json", videojuego)
        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarVideojuego(@PathVariable id: Long) : ResponseEntity<Videojuego> {
        val videojuego = videojuegoServiceAPI.get(id)
        if (videojuego != null) {
            videojuegoServiceAPI.eliminar(id)
            videojuegoServiceAPI.guardar("videojuegos.json", videojuego)
        } else {
            return ResponseEntity<Videojuego>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }
}