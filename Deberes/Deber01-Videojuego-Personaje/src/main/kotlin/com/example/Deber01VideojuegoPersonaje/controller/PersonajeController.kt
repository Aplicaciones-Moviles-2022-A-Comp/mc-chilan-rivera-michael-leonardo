package com.example.Deber01VideojuegoPersonaje.controller

import com.example.Deber01VideojuegoPersonaje.model.Personaje
import com.example.Deber01VideojuegoPersonaje.service.api.PersonajeServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/personaje")
@CrossOrigin("*")
class PersonajeController {
    @Autowired
    lateinit var personajeServiceAPI: PersonajeServiceAPI

    @GetMapping("/lista_de_personajes")
    fun obtenerPersonajes() : MutableList<Personaje>? {
        return personajeServiceAPI.obtenerTodo
    }

    @PostMapping("/crearActualizar")
    fun crearActualizarPersonaje(@RequestBody personaje: Personaje) : ResponseEntity<Personaje> {
        var obj = personajeServiceAPI.crearActualizar(personaje)
        return ResponseEntity<Personaje>(personaje, HttpStatus.OK)
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarPersonaje(@PathVariable id: Long) : ResponseEntity<Personaje> {
        val personaje = personajeServiceAPI.get(id)
        if (personaje != null) {
            personajeServiceAPI.eliminar(id)
        } else {
            return ResponseEntity<Personaje>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Personaje>(personaje, HttpStatus.OK)
    }
}