package com.example.Deber01VideojuegoPersonaje.commons

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.io.File
import java.io.Serializable
import java.util.function.Consumer

@Service
abstract class GenericServiceImpl<T, ID : Serializable?> : GenericServiceAPI<T, ID>{ // implementación de las funciones que contiene la interface GenericServiceAPU
    override fun crearActualizar(entity: T): T {
        return getDao().save(entity)
    }
    override fun eliminar(id: ID) {
        getDao().deleteById(id)
    }
    override fun get(id: ID): T? {
        val obj = getDao().findById(id)
        return if (obj.isPresent) {
            obj.get()
        } else null
    }

    override fun guardar(nombre_archivo: String, entity: T) {
        val file = "C:\\Users\\michael.chilan\\Desktop\\AplicacionesMoviles\\mc-chilan-rivera-michael-leonardo\\Deberes\\Deber01-Videojuego-Personaje\\src\\main\\kotlin\\com\\example\\Deber01VideojuegoPersonaje\\"
        val my_file = File(file + nombre_archivo)

        my_file.printWriter().use { out ->
            obtenerTodo?.forEach {
                val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                val jsonTutsListPretty: String = gsonPretty.toJson(it)
                out.println(jsonTutsListPretty)
            }
        }
    }

    override fun leerArchivo(nombre_archivo: String): MutableList<T> {
        val gson = Gson()
        val file = "C:\\Users\\michael.chilan\\Desktop\\AplicacionesMoviles\\mc-chilan-rivera-michael-leonardo\\Deberes\\Deber01-Videojuego-Personaje\\src\\main\\kotlin\\com\\example\\Deber01VideojuegoPersonaje\\"
        val mutableListTutorialType = object: TypeToken<MutableList<T>>(){}.type
        return gson.fromJson(file + nombre_archivo, mutableListTutorialType)
    }

    override val obtenerTodo: MutableList<T>?
        get() {
            val returnList: MutableList<T> = ArrayList()
            getDao().findAll().forEach(Consumer { obj: T -> returnList.add(obj) })
            return returnList
        }

    abstract fun getDao() : CrudRepository<T, ID>
}