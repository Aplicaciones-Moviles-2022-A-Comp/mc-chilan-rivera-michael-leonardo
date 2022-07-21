package com.example.examen_1b

import android.os.Parcel
import android.os.Parcelable

class Personaje (
    val idPersonaje: Int,
    var fechaNacimiento: String?,
    var personajePrincipal: String?,
    var nombre: String?,
    var edadIncial: String?,
    var altura: String?
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
        parcel.writeInt(idPersonaje)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(personajePrincipal)
        parcel.writeString(nombre)
        parcel.writeString(edadIncial)
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