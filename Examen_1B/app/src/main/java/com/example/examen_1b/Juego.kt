package com.example.examen_1b

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

class Juego(
    val idJuego: Int,
    var fechaLanzamiento: String?,
    var aptoTodoPublico: String?,
    var nombre: String?,
    var horasJuegoHistoria: String?,
    var precio: String?
):Parcelable {
    override fun toString(): String {
        return "${nombre}"
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){}
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idJuego)
        parcel.writeString(fechaLanzamiento)
        parcel.writeString(aptoTodoPublico)
        parcel.writeString(nombre)
        parcel.writeString(horasJuegoHistoria)
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