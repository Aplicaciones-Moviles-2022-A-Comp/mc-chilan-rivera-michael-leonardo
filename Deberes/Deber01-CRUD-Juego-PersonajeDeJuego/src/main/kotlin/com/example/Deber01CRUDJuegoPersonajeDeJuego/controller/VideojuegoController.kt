package com.example.Deber01CRUDJuegoPersonajeDeJuego.controller

import com.example.Deber01CRUDJuegoPersonajeDeJuego.model.Videojuego
import com.example.Deber01CRUDJuegoPersonajeDeJuego.repository.VideojuegoRepository
import com.example.Deber01CRUDJuegoPersonajeDeJuego.service.api.VideojuegoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
class VideojuegoController {

    @Autowired
    lateinit var videojuegoServiceAPI: VideojuegoServiceAPI

    @GetMapping("/all")
    fun getAll() : MutableList<Videojuego>? {
        return videojuegoServiceAPI.all
    }

    @PostMapping("/save")
    fun save(@RequestBody videojuego: Videojuego) : ResponseEntity<Videojuego> {
        var obj = videojuegoServiceAPI.save(videojuego)
        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Videojuego> {
        val videojuego = videojuegoServiceAPI.get(id)
        if (videojuego != null) {
            videojuegoServiceAPI.delete(id)
        } else {
            return ResponseEntity<Videojuego>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Videojuego>(videojuego, HttpStatus.OK)
    }

    @PostMapping ("/update/{id}")
    fun update(@PathVariable id:Long,@RequestBody videojuego:Videojuego):ResponseEntity<Videojuego> {
        return videojuegoServiceAPI.get(id).map { existingPost ->
            val updatedPost: Post = existingPost
                .copy(title = newPost.title, content = newPost.content)

            ResponseEntity.ok().body(postRepository.save(updatedPost))
        }.orElse(ResponseEntity.notFound().build())
    }
}