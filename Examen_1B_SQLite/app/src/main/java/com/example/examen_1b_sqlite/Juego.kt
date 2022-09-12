package com.example.examen_1b_sqlite

import android.os.Parcel
import android.os.Parcelable

class Juego(
    var idJuego: Int?,
    var fechaLanzamiento: String?,
    var aptoTodoPublico: String?,
    var nombre: String?,
    var horasJuegoHistoria: Int?,
    var precio: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ){}
    override fun toString(): String {
        return "${nombre} - ${fechaLanzamiento}"
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if(idJuego != null) {
            parcel.writeInt(idJuego!!) // val conexionEscritura = this.writableDatabase
        } else {
            parcel.writeInt(0)
        }
        parcel.writeString(fechaLanzamiento)
        parcel.writeString(aptoTodoPublico)
        parcel.writeString(nombre)
        parcel.writeInt(horasJuegoHistoria!!)
        parcel.writeString(precio)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Juego> {
        override fun createFromParcel(parcel: Parcel): Juego {
            return Juego(parcel)
        }

        override fun newArray(size: Int): Array<Juego?> {
            return arrayOfNulls(size)
        }
    }
}