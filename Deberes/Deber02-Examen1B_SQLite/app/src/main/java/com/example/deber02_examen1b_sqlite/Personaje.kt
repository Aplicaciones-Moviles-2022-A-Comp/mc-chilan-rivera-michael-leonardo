package com.example.deber02_examen1b_sqlite

import android.os.Parcel
import android.os.Parcelable

class Personaje (
    var idPersonaje: Int,
    var fechaNacimiento: String?,
    var personajePrincipal: String?,
    var nombrePersonaje: String?,
    var edadIncial:Int,
    var altura: String?
): Parcelable {
    override fun toString(): String {
        return "${nombrePersonaje}"
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
        parcel.writeInt(idPersonaje)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(personajePrincipal)
        parcel.writeString(nombrePersonaje)
        parcel.writeInt(edadIncial)
        parcel.writeString(altura)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }
}