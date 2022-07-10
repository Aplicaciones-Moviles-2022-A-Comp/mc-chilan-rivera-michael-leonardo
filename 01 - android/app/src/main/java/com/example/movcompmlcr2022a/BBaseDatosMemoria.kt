package com.example.movcompmlcr2022a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1, "Michael", "m@m.com"))
            arregloBEntrenador.add(BEntrenador(2, "Leonardo", "l@l.com"))
            arregloBEntrenador.add(BEntrenador(3, "LiLiana", "lL@lL.com"))
        }
    }
}