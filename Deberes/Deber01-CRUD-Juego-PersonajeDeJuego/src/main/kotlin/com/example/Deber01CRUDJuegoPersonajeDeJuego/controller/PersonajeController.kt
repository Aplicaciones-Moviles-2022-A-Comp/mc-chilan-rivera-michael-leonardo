package com.example.Deber01CRUDJuegoPersonajeDeJuego.controller

import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Personaje
import com.example.Deber01CRUDJuegoPersonajeDeJuego.repository.PersonajeRepository
import com.example.Deber01CRUDJuegoPersonajeDeJuego.service.api.PersonajeServiceAPI
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
@RequestMapping("/api/v2")
@CrossOrigin("*")
class PersonajeController {
    @Autowired
    lateinit var personajeServiceAPI: PersonajeServiceAPI

    @GetMapping("/all")
    fun getAll() : MutableList<Personaje>? {
        return personajeServiceAPI.all
    }

    @PostMapping("/save")
    fun save(@RequestBody personaje: Personaje) : ResponseEntity<Personaje> {
        var obj = personajeServiceAPI.save(personaje)
        return ResponseEntity<Personaje>(personaje, HttpStatus.OK)
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Personaje> {
        val personaje = personajeServiceAPI.get(id)
        if (personaje != null) {
            personajeServiceAPI.delete(id)
        } else {
            return ResponseEntity<Personaje>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Personaje>(personaje, HttpStatus.OK)
    }

    @PostMapping ("/update/{id}")
    fun update(@PathVariable id:Long,@RequestBody personaje:Personaje):ResponseEntity<Personaje> {
        //val personaje = personajeServiceAPI.get(id)
        if (id != null) {
            personajeServiceAPI.update(personaje, id)

        } else {
            return ResponseEntity<Personaje>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Personaje>(personaje, HttpStatus.OK)
    }
}