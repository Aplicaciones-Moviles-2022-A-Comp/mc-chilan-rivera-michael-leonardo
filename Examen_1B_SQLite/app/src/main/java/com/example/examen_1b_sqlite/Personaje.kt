package com.example.examen_1b_sqlite

import android.os.Parcel
import android.os.Parcelable

class Personaje (
    var idPersonaje: Int?,
    var idJuego: Int?,
    var fechaNacimiento: String?,
    var personajePrincipal: String?,
    var nombre: String?,
    var edadIncial: Int?,
    var altura: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ){}
    override fun toString(): String {
        return "${nombre}-${fechaNacimiento}"
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if(idPersonaje != null) {
            parcel.writeInt(idPersonaje!!)
        } else {
            parcel.writeInt(0)
        }
        parcel.writeInt(idJuego!!)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(personajePrincipal)
        parcel.writeString(nombre)
        parcel.writeInt(edadIncial!!)
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