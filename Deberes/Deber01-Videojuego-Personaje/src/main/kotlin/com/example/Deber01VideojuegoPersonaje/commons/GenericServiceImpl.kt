package com.example.Deber01VideojuegoPersonaje.commons

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
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

    override val obtenerTodo: MutableList<T>?
        get() {
            val returnList: MutableList<T> = ArrayList()
            getDao().findAll().forEach(Consumer { obj: T -> returnList.add(obj) })
            return returnList
        }

    abstract fun getDao() : CrudRepository<T, ID>
}