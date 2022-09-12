package com.example.deber02_examen1b_sqlite

import android.os.Parcel
import android.os.Parcelable

class JuegoPersonaje (
    val idJuegoPersonaje: Int,
    var nombreJuegoPersonaje: String?,
    var idJuego: Int,
    var idPersonaje: Int
): Parcelable {
    override fun toString(): String {
        return "${nombreJuegoPersonaje}"
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idJuegoPersonaje)
        parcel.writeString(nombreJuegoPersonaje)
        parcel.writeInt(idJuego)
        parcel.writeInt(idPersonaje)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<JuegoPersonaje> {
        override fun createFromParcel(parcel: Parcel): JuegoPersonaje {
            return JuegoPersonaje(parcel)
        }

        override fun newArray(size: Int): Array<JuegoPersonaje?> {
            return arrayOfNulls(size)
        }
    }
}