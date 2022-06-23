package archivo

import model.Juego
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStream

fun escribirEnArchivo(dato: Any) {
    val gsonPretty = GsonBuilder().setPrettyPrinting().create()
    val jsonTutsListPretty: String = gsonPretty.toJson(dato)
    try {
        File("Deber01.txt").printWriter().use { out ->
            out.println(jsonTutsListPretty)
        }
        File("Deber01.json").printWriter().use { out ->
            out.println(jsonTutsListPretty)
        }
    } catch (e: IOException) {
        print(e)
    }
}

fun leerArchivo(): MutableList<Juego> {
    val gson = Gson()
    val inputStream: InputStream = File("Deber01.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    val mutableListTutorialType = object : TypeToken<MutableList<Juego>>() {}.type
    return gson.fromJson(inputString, mutableListTutorialType)
}