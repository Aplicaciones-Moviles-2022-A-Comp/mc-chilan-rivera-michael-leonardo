package com.example.Deber01CRUDJuegoPersonajeDeJuego.commons

import com.example.Deber01CRUDJuegoPersonajeDeJuego.commons.GenericServiceAPI
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.ArrayList
import java.util.function.Consumer
import javax.persistence.Column

@Service
abstract class GenericServiceImpl<T, ID : Serializable?> : GenericServiceAPI<T, ID> {
    override fun save(entity: T): T {
        return getDao().save(entity)
    }
    override fun delete(id: ID) {
        getDao().deleteById(id)
    }
    override fun get(id: ID): T? {
        val obj = getDao().findById(id)
        return if (obj.isPresent) {
            obj.get()
        } else null
    }

    override val all: MutableList<T>
        get() {
            val returnList: MutableList<T> = ArrayList()
            getDao().findAll().forEach(Consumer { obj: T -> returnList.add(obj) })
            return returnList
        }
    abstract fun getDao(): CrudRepository<T, ID>
}