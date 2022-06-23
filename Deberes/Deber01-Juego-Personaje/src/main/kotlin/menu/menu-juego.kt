package menu

import archivo.*
import model.*
import java.text.SimpleDateFormat
import javax.swing.JOptionPane

fun menuInicio(): String {
    return "     GESTIÓN DE VIDEOJUEGOS      \n\n" +
            "MENÚ DE OPCIONES:\n \n" +
            "1. Crear Juego\n" +
            "2. Actualizar Juego\n" +
            "3. Eliminar Juego\n" +
            "4. Buscar Juego por nombre\n" +
            "5. Listar todos los Juegos\n" +
            "6. Gestionar Personajes\n" +
            "7. Salir\n"
}
fun crearJuegos() {
    val fechaLanzamiento = JOptionPane.showInputDialog("Ingrese la fecha de lanzamiento del Juego, con el siguiente formato 'YYYY-MM-DDD'")
    val aptoTodoPublico = aptoTodoPublico()
    val nombre = JOptionPane.showInputDialog("Indique el nombre del Juego")
    val horasJuegoHistoria = JOptionPane.showInputDialog("Indique el número de horas en la historia del Juego")
    val precio = JOptionPane.showInputDialog("Indique el precio del juego")

    val datos = leerArchivo()

    val formato = SimpleDateFormat("yyyy-MM-dd")
    val fecha = formato.parse(fechaLanzamiento)

    val juegoNuevo = crearJuego(fecha, aptoTodoPublico, nombre, horasJuegoHistoria.toInt(), precio.toDouble())
    val nuevo = datos + juegoNuevo
    escribirEnArchivo(nuevo)
}

fun actualizarJuegos() {
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del Juego a actualizar")
    val atributo = JOptionPane.showInputDialog("Ingrese el atributo del Juego a actualizar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor")
    val datos = leerArchivo()
    val juegoEditado = actualizarJuego(nombre, atributo, nuevoValor, datos)
    escribirEnArchivo(juegoEditado)
}

fun eliminarJuegos() {
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre deL Juego a eliminar")
    val datos = leerArchivo()
    val eliminarJuego = eliminarJuego(nombre, datos)
    escribirEnArchivo(eliminarJuego)
}

fun buscarJuegos() {
    val consulta = JOptionPane.showInputDialog("Ingrese el nombre del Juego para realizar la búsqueda")
    val datos = leerArchivo()
    val juegoEncontrado = buscarJuego(consulta, datos)
    val existe = juegoEncontrado.isNotEmpty()
    if (existe) {
        var menuRespuesta = ""
        juegoEncontrado.forEach { juego: Juego? ->
            menuRespuesta += "-------------------------------------------------\n" +
                    "Fecha de Lanzamiento: ${juego?.fechaLanzamiento}\n" +
                    "Apto para todo el Público: ${juego?.aptoTodoPublico}\n" +
                    "Nombre: ${juego?.nombre}\n" +
                    "Número de Horas en la Historia: ${juego?.horasJuegoHistoria}\n" +
                    "Precio: ${juego?.precio}\n" +
                    "Número de Personajes: ${juego?.personajes?.size}\n"
        }
        menuRespuesta += "-------------------------------------------------\n"
        JOptionPane.showMessageDialog(null, menuRespuesta)
    } else {
        JOptionPane.showMessageDialog(null, "Juego NO encontrado")
    }
}

fun mostrarTodo() {
    val total = listarTodo()
    JOptionPane.showMessageDialog(null, total)
}

fun aptoTodoPublico(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Apto para todo el público ",
        "Apto",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}
