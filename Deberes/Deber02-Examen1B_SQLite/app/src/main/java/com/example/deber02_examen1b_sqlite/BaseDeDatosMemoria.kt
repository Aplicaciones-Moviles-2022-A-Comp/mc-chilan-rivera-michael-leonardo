package com.example.deber02_examen1b_sqlite

class BaseDeDatosMemoria {
    companion object{
        var arregloJuegoPersonaje = arrayListOf<JuegoPersonaje>()

        init {
            // cargar datos del juego
            BaseDeDatos.TablaJuego!!.crearJuego(1, "29/05/2014", "Sí", "Mario Kart 8", 200, "12.23")
            BaseDeDatos.TablaJuego!!.crearJuego(2, "13/11/2007", "No", "Assassin's Creed", 100, "20.23")
            BaseDeDatos.TablaJuego!!.crearJuego(3, "17/11/2002", "No", "Tom Clancy's Splinter Cell", 150, "19.20")

            // cargar datos del personaje
            BaseDeDatos.TablaJuego!!.crearPersonaje(1, "24/06/1459", "Sí", "Ezio Auditore", 19, "1.73")
            BaseDeDatos.TablaJuego!!.crearPersonaje(2, "13/09/1985", "Sí", "Mario Bros.", 30, "1.52")
            BaseDeDatos.TablaJuego!!.crearPersonaje(3, "23/04/1993", "Sí", "Sam Fisher", 30, "1.83")

            // cargar datos JuegoPersonaje
            arregloJuegoPersonaje.add(JuegoPersonaje(1, "Mario Bros.", 1, 2))
            arregloJuegoPersonaje.add(JuegoPersonaje(2, "Ezio Auditore", 2, 1))
            arregloJuegoPersonaje.add(JuegoPersonaje(3, "Sam Fisher", 3, 3))
        }
    }
}