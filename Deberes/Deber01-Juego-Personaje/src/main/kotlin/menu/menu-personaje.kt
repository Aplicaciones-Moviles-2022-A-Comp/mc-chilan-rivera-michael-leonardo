@file:Suppress("UNCHECKED_CAST")

package menu

import archivo.*
import model.*
import java.text.SimpleDateFormat
import javax.swing.JOptionPane

fun menuInicioPersonaje(): String {
    return "      Gestión de Personajes      \n\n" +
            "MENÚ DE OPCIONES :\n \n" +
            "1. Crear Personaje\n" +
            "2. Actualizar Personaje\n" +
            "3. Eliminar Personaje\n" +
            "4. Buscar Personaje por nombre\n" +
            "5. Mostrar datos\n" +
            "0. Volver a menu Juegos\n"
}

fun crearPersonajes() {
    val datos = leerArchivo()

    val fechaNacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del Personaje, con el siguiente formato 'YYYY-MM-DDD'")
    val personajePrincipal = personajePrincipal()
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del Personaje")
    val nombreJuego = JOptionPane.showInputDialog("Ingrese el nombre del Juego en que se agregará al Personaje")
    val edadInicial = JOptionPane.showInputDialog("Ingrese la edad inicial del Personaje al empezar el Juego")
    val altura = JOptionPane.showInputDialog("Ingrese la altura del Personaje")

    val formato = SimpleDateFormat("yyyy-MM-dd")
    val fecha = formato.parse(fechaNacimiento)

    val personaje = Personaje(fecha,personajePrincipal, nombre, nombreJuego, edadInicial.toInt(), altura.toDouble())
    val personajeCreado = crearPersonaje(nombreJuego, personaje, datos)
    escribirEnArchivo(personajeCreado)
}

fun actualizarPersonajes() {
    val datos = leerArchivo()

    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del Personaje a editar")
    val atributo = JOptionPane.showInputDialog("Ingrese el atributo del Personaje a editar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor del Personaje a editar")
    val respuesta = actualizarPersonaje(nombre, atributo, nuevoValor, datos)
    escribirEnArchivo(respuesta)
}

fun eliminarPersonajes() {
    val datos = leerArchivo()
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del Personaje a eliminar")
    val respuesta = eliminarPersonaje(nombre, datos)
    escribirEnArchivo(respuesta)
}

fun buscarPersonajes() {
    val datos = leerArchivo()
    val consulta = JOptionPane.showInputDialog("Ingrese el dato a buscar")
    val respuesta = buscarPersonaje(consulta, datos)
    var stringRespuesta = ""
    respuesta.forEach { list ->
        val juego: Juego = list?.get(0) as Juego
        val arregloPersonajes: List<Personaje> = list[1] as List<Personaje>
        val nombrePersonaje = arregloPersonajes.map { personaje: Personaje ->
            val arregloDatos = mutableMapOf<String, Any>()
            arregloDatos.put("Fecha de Nacimiento", personaje.fechaNacimiento)
            arregloDatos.put("Personaje Principal", personaje.personajePrincipal)
            arregloDatos.put("Nombre", personaje.nombre)
            arregloDatos.put("Nombre del Juego", personaje.nombreJuego)
            arregloDatos.put("Edad inicial del Personaje", personaje.edadInicial)
            arregloDatos.put("Altura del Personaje", personaje.altura)
            return@map arregloDatos
        }
        stringRespuesta += "-----------------------------------------------------------------------------------------\n" +
                "Juego: ${juego.nombre}\n" +
                "Personaje: ${nombrePersonaje}\n"
    }
    stringRespuesta += "-----------------------------------------------------------------------------------------\n"
    JOptionPane.showMessageDialog(null, stringRespuesta)
}



fun personajePrincipal(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Personaje Principal ",
        "Personaje Principal",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}
