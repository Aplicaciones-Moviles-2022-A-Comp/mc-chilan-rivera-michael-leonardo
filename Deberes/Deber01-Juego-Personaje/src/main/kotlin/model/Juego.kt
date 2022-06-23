package model

import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import javax.swing.JOptionPane

@Suppress("DEPRECATION")
class Juego (
    var fechaLanzamiento: Date = Date(0,0,0),
    var aptoTodoPublico: Boolean = false,
    var nombre: String = "",
    var horasJuegoHistoria: Int = 0,
    var precio: Double = 0.0,
    var personajes: MutableList<Personaje>? = null
) {}

fun crearJuego(
    fechaLanzamiento: Date,
    aptoTodoPublico: Boolean,
    nombre: String,
    horasJuegoHistoria: Int,
    precio: Double
): MutableList<Juego> {
    val personajes = mutableListOf<Personaje>()
    return mutableListOf(
        Juego(
            fechaLanzamiento,
            aptoTodoPublico,
            nombre,
            horasJuegoHistoria,
            precio,
            personajes=personajes
        )
    ) }

fun eliminarJuego(
    nombre: String,
    juegos: MutableList<Juego>
): MutableList<Juego> {
    val indice = buscarIndice(nombre, juegos)
    val existeJuego = indice > -1
    if(existeJuego) {
        juegos.removeAt(indice)
    }
    return juegos
}

fun actualizarJuego (
    nombre: String,
    datoEditar: String,
    nuevoValor: String,
    juegos: MutableList<Juego>
): MutableList<Juego> {
    val indice = buscarIndice(nombre, juegos)
    val existeJuego = indice > -1
    if(existeJuego) {
        when(datoEditar) {
            "fecha_lanzamiento" -> {
                val formato = SimpleDateFormat("yyyy-MM-dd")
                val fecha = formato.parse(nuevoValor)
                juegos[indice].fechaLanzamiento = fecha
            }
            "apto_todo_publico" -> {
                juegos[indice].aptoTodoPublico = nuevoValor.toBoolean()
            }
            "nombre" -> {
                juegos[indice].nombre = nuevoValor
            }
            "horas_juego_historia" -> {
                juegos[indice].horasJuegoHistoria = nuevoValor.toInt()
            }
            "precio" -> {
                juegos[indice].precio = nuevoValor.toDouble()
            }
            else -> {
                JOptionPane.showMessageDialog(null, "No es encontró el videojuego $nombre o el atributo $datoEditar")
            }
        }
    }
    return juegos
}

fun buscarJuego(
    datoConsulta: String,
    juegos: MutableList<Juego>
): List<Juego> {
    var juegosEncontrados: List<Juego> = emptyList()
    when(val parametro = "nombre") {
        "nombre" -> {
            juegosEncontrados = juegos.filter { juego: Juego ->
                return@filter juego.nombre == datoConsulta
            }
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Parámetro de búsqueda ${parametro} no encontrado")
        }
    }
    return juegosEncontrados
}

fun buscarIndice(nombre: String, juegos: MutableList<Juego>): Int {
    val respuesta = juegos.filter { juego: Juego ->
        return@filter juego.nombre == nombre
    }
    val existeJuego = respuesta.size > 0
    if(!existeJuego) {
        JOptionPane.showMessageDialog(null, "No se encontró al Juego ${nombre}")
        return -1
    }
    return juegos.indexOf(respuesta[0])
}

fun listarTodo(): String {
    val inputStream: InputStream = File("Deber01.json").inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}