package com.example.deber02_examen1b_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelperJuegoPersonaje(
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaJuego:ArrayList<String> = arrayListOf(
            """
                CREATE TABLE JUEGO(
                idJuego INTEGER PRIMARY KEY AUTOINCREMENT,
                fechaLanzamiento VARCHAR(50),
                aptoTodoPublico VARCHAR(50),
                nombreJuego VARCHAR(50),
                horasJuegoHistoria INTEGER,
                precio VARCHAR(50)
                );
            """ , """
                CREATE TABLE PERSONAJE(
                idPersonaje INTEGER PRIMARY KEY AUTOINCREMENT,
                fechaNacimiento VARCHAR(50),
                personajePrincipal VARCHAR(50),
                nombrePersonaje VARCHAR(50),
                edadInicial INTEGER,
                altura VARCHAR(50)
                );
            """)
        for (i in scriptSQLCrearTablaJuego) {
            db!!.execSQL(i)
        }
        Log.i("creart", "Juegos")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearJuego(
        idJuego: Int,
        fechaLanzamiento: String,
        aptoTodoPublico: String,
        nombreJuego: String,
        horasJuegoHistoria: Int,
        precio: String?
    ): Boolean {
        //this.readableDatabase Lectura
        //this.writableDatabase Escritura
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idJuego", idJuego)
        valoresAGuardar.put("fechaLanzamiento", fechaLanzamiento)
        valoresAGuardar.put("aptoTodoPublico", aptoTodoPublico)
        valoresAGuardar.put("nombreJuego", nombreJuego)
        valoresAGuardar.put("horasJuegoHistoria", horasJuegoHistoria)
        valoresAGuardar.put("precio", precio)
        val resultadoGuardar = baseDatosEscritura.insert("JUEGO", null, valoresAGuardar)
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun listarJuegos(): ArrayList<Juego>{
        var lista = arrayListOf<Juego>()
        var juego: Juego?
        val baseDatosLectura = readableDatabase
        val scriptConsultarJuego = "SELECT * FROM JUEGO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarJuego,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                juego = Juego(0,"","","",0,"")
                juego!!.idJuego = resultadoConsultaLectura.getInt(0)
                juego.fechaLanzamiento= resultadoConsultaLectura.getString(1)
                juego.aptoTodoPublico= resultadoConsultaLectura.getString(2)
                juego.nombreJuego= resultadoConsultaLectura.getString(3)
                juego.horasJuegoHistoria= resultadoConsultaLectura.getString(4).toInt()
                juego.precio= resultadoConsultaLectura.getString(5)
                lista.add(juego)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun eliminarJuego(id: Int): Boolean{
        //        val conexionEscritura = this.writableDatabase
        var lista = BaseDeDatos.TablaJuego!!.listarJuegos()
        val idR = lista[id].idJuego.toString()
        val conexionEscritura = writableDatabase
        // "SELECT * FROM USUARIO WHERE ID = ?"
        // arrayOf(
        //    id.toString()
        // )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "JUEGO",
                "idJuego=?",
                arrayOf(
                    idR
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun actualizarJuego(
        fechaLanzamiento: String,
        aptoTodoPublico: String,
        nombreJuego: String,
        horasJuegoHistoria: Int,
        precio: String?,
        idActualizar: Int
    ): Boolean {
        var lista = BaseDeDatos.TablaJuego!!.listarJuegos()
        val id = lista[idActualizar].idJuego.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("fechaLanzamiento", fechaLanzamiento)
        valoresAActualizar.put("aptoTodoPublico", aptoTodoPublico)
        valoresAActualizar.put("nombreJuego", nombreJuego)
        valoresAActualizar.put("horasJuegoHistoria", horasJuegoHistoria)
        valoresAActualizar.put("precio", precio)
        val resultadoActualizacion = conexionEscritura
            .update(
                "JUEGO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "idJuego=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun crearPersonaje(
        idPersonaje: Int,
        fechaNacimiento: String,
        personajePrincipal: String,
        nombrePersonaje: String,
        edadInicial: Int,
        altura: String?
    ): Boolean {
        //this.readableDatabase Lectura
        //this.writableDatabase Escritura
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idPersonaje", idPersonaje)
        valoresAGuardar.put("fechaNacimiento", fechaNacimiento)
        valoresAGuardar.put("personajePrincipal", personajePrincipal)
        valoresAGuardar.put("nombrePersonaje", nombrePersonaje)
        valoresAGuardar.put("edadInicial", edadInicial)
        valoresAGuardar.put("altura", altura)
        val resultadoGuardar = baseDatosEscritura.insert("PERSONAJE", null, valoresAGuardar)
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun listarPersonajes(): ArrayList<Personaje>{
        var lista = arrayListOf<Personaje>()
        var personaje: Personaje?
        val baseDatosLectura = readableDatabase
        val scriptConsultarPersonaje = "SELECT * FROM PERSONAJE"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarPersonaje,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                personaje = Personaje(0,"","","",0,"")
                personaje!!.idPersonaje = resultadoConsultaLectura.getInt(0)
                personaje.fechaNacimiento = resultadoConsultaLectura.getString(1)
                personaje.personajePrincipal = resultadoConsultaLectura.getString(2)
                personaje.nombrePersonaje = resultadoConsultaLectura.getString(3)
                personaje.edadIncial = resultadoConsultaLectura.getInt(4)
                personaje.altura = resultadoConsultaLectura.getString(5)
                lista.add(personaje)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun eliminarPersonaje(id: Int): Boolean{
        //        val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        // "SELECT * FROM USUARIO WHERE ID = ?"
        // arrayOf(
        //    id.toString()
        // )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PERSONAJE",
                "idPersonaje=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun actualizarPersonaje(
        fechaNacimiento: String,
        personajePrincipal: String,
        nombrePersonaje: String,
        edadInicial: Int,
        altura: String?,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("fechaNacimiento", fechaNacimiento)
        valoresAActualizar.put("personajePrincipal", personajePrincipal)
        valoresAActualizar.put("nombrePersonaje", nombrePersonaje)
        valoresAActualizar.put("edadInicial", edadInicial)
        valoresAActualizar.put("altura", altura)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PERSONAJE", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "idPersonaje=?", // Clausula Where
                arrayOf(
                    idActualizar.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1   //return if (resultadoActualizacion == -1) false else true
    }
}