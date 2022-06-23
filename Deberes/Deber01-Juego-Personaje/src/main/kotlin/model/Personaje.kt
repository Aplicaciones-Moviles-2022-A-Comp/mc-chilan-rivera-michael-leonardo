package model

import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JOptionPane

class Personaje (
    var fechaNacimiento: Date = Date(0,0,0),
    var personajePrincipal: Boolean = false,
    var nombre: String = "",
    var nombreJuego: String = "",
    var edadInicial: Int = 0,
    var altura: Double = 0.0
) {}

fun crearPersonaje(
    nombreJuego: String,
    personaje: Personaje,
    juegos: MutableList<Juego>
): MutableList<Juego> {
    val indiceJuego = buscarIndice(nombreJuego, juegos)
    val existeJuego = indiceJuego > -1
    if(existeJuego) {
        juegos[indiceJuego].personajes?.add(personaje)
    }
    return juegos
}

fun eliminarPersonaje(
    nombre: String,
    juegos: MutableList<Juego>
): MutableList<Juego> {
    val indice = buscarIndices(nombre, juegos)
    val existeJuego = indice["juego"]!! > -1
    val existePersonaje = indice["personaje"]!! > -1

    if(existeJuego && existePersonaje) {
        val indiceJuego = indice["juego"] as Int
        val indicePersonaje = indice["personaje"] as Int
        juegos[indiceJuego].personajes?.removeAt(indicePersonaje)
    }
    return juegos
}

fun actualizarPersonaje (
    nombre: String,
    datoEditar: String,
    nuevoValor: String,
    juegos: MutableList<Juego>
): MutableList<Juego> {
    val indice = buscarIndices(nombre, juegos)
    val existeJuego = indice["juego"]!! > -1
    if(existeJuego) {
        val indiceJuego = indice["juego"] as Int
        val indicePersonaje = indice["personaje"] as Int
        when(datoEditar) {
            "fecha_nacimiento" -> {
                val formato = SimpleDateFormat("yyyy-MM-dd")
                val fecha = formato.parse(nuevoValor)
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.fechaNacimiento = fecha
            }
            "personaje_principal" -> {
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.personajePrincipal = nuevoValor.toBoolean()
            }
            "nombre" -> {
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.nombre = nuevoValor
            }
            "nombreJuego" -> {
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.nombreJuego = nuevoValor
            }
            "edad_inicial" -> {
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.edadInicial = nuevoValor.toInt()
            }
            "altura" -> {
                juegos[indiceJuego].personajes?.get(indicePersonaje)?.altura = nuevoValor.toDouble()
            }
            else -> {
                JOptionPane.showMessageDialog(null, "No es encontró el Juego $nombre o el atributo $datoEditar")
            }
        }
    }
    return juegos
}

fun buscarPersonaje(
    datoConsulta: String,
    juegos: MutableList<Juego>
): List<List<Any>?> {
    var juegosEncontrados: List<List<Any>?> = emptyList()
    when(val parametro = "nombre") {
        "nombre" -> {
            juegosEncontrados = juegos.map { juego: Juego ->
                val juegoPersonaje = juego.personajes?.filter { personaje: Personaje ->
                    return@filter personaje.nombre == datoConsulta
                }
                return@map juegoPersonaje?.let { listOf<Any>(juego, it) }
            }.filter { list: List<Any>? ->
                return@filter list != null
            }.filter { list ->
                val personajesFiltrados = list?.get(1) as List<Personaje>
                return@filter personajesFiltrados.size > 0
            }
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Parámetro ${parametro} no encontrado")
        }
    }
    return juegosEncontrados
}

fun buscarIndices(nombre: String, juegos: MutableList<Juego>): Map<String, Int?> {
    val respuesta = juegos.map { juego: Juego ->
        val juegoPersonaje = juego.personajes?.filter { personaje: Personaje ->
            return@filter personaje.nombre == nombre
        }
        return@map juegoPersonaje?.let { listOf<Any>(juego, it) }
    }.filter { list: List<Any>? ->
        return@filter list != null
    }.filter { list ->
        val personajesFiltrados = list?.get(1) as List<*>
        return@filter personajesFiltrados.isNotEmpty()
    }
    val respuestaEncontrada = respuesta.isNotEmpty()
    if(!respuestaEncontrada) {
        JOptionPane.showMessageDialog(null, "No se encontró el personaje con el nombre ${nombre}")
        return mapOf<String, Int?>("juego" to -1, "personaje" to -1)
    }
    val indiceJuego = juegos.indexOf(respuesta[0]?.get(0))
    val personaje = respuesta[0]?.get(1) as List<*>
    val indicePersonaje = juegos[indiceJuego].personajes?.indexOf(personaje[0])
    val indice = mapOf<String, Int?>("juego" to indiceJuego, "personaje" to indicePersonaje)
    return indice
}