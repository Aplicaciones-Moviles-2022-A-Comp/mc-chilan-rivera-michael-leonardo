package com.example.deber02_examen1b_sqlite

import android.os.Parcel
import android.os.Parcelable

class Juego(
    var idJuego: Int,
    var fechaLanzamiento: String?,
    var aptoTodoPublico: String?,
    var nombreJuego: String?,
    var horasJuegoHistoria: Int,
    var precio: String?
): Parcelable {
    override fun toString(): String {
        return "${nombreJuego}"
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ){}
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idJuego)
        parcel.writeString(fechaLanzamiento)
        parcel.writeString(aptoTodoPublico)
        parcel.writeString(nombreJuego)
        parcel.writeInt(horasJuegoHistoria)
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