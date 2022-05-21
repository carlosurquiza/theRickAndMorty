package cl.cdum.therickandmorty.utils.functions

import java.util.*

val exclusions: List<String> = listOf(
    "a", "ante", "bajo", "cabe", "con", "contra", "de", "desde",
    "durante", "en", "entre", "hacia", "hasta", "mediante",
    "para", "por", "según", "sin", "so", "sobre", "tras", "versus",
    "vía", "el", "la", "los", "las", "un", "una", "unos", "unas", "al", "del"
)

fun String.capitalizeEachWord(): String {
    return if (this.isEmpty()) {
        this
    } else {
        val stringArray = this.lowercase().split(" ").toMutableList()

        stringArray.forEachIndexed { index, s ->
            if (!exclusions.contains(s)) {
                if (s.contains(".")) {
                    stringArray[index] = s.uppercase()
                } else {
                    stringArray[index] = s.capitalized()
                }
            }
        }

        stringArray.joinToString(" ")
    }
}

fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}