import menu.*
import javax.swing.JOptionPane

fun main(args: Array<String>) {
    menu()
}

fun menu() {
    val menuInicio = menuInicio()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicio)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearJuegos()
            }
            2 -> {
                actualizarJuegos()
            }
            3 -> {
                eliminarJuegos()
            }
            4 -> {
                buscarJuegos()
            }
            5 -> {
                mostrarTodo()
            }
            6 -> {
                menuPersonajes()
            }
            7 ->{
                JOptionPane.showMessageDialog(null, "GRACIAS POR VISITARNOS")

            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion != 7)
}

fun menuPersonajes() {
    val menuInicioEstudiante = menuInicioPersonaje()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicioEstudiante)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearPersonajes()
            }
            2 -> {
                actualizarPersonajes()
            }
            3 -> {
                eliminarPersonajes()
            }
            4 -> {
                buscarPersonajes()
            }
            5 -> {
                mostrarTodo()
            }
            0 -> {
                if(regresarMenuPrincipal()){
                    menu()
                } else {
                    JOptionPane.showMessageDialog(null, "Ha seleccionado la opcion no, siga administranddo personajes")
                }
            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion == 0)
}

fun regresarMenuPrincipal(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Esta seguro que desea regresar al menu principal ",
        "Regresar Menu Principal",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}