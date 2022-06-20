package com.example.movcompmlcr2022a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador("Michael", "m@m.com"))
            arregloBEntrenador.add(BEntrenador("Leonardo", "l@l.com"))
            arregloBEntrenador.add(BEntrenador("LiLiana", "lL@lL.com"))
        }
    }
}