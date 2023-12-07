package org.springkotlin

fun returnNothting(): Nothing {
    throw RuntimeException("Exception")
}

fun main() {
    println(nameLength("Seungjae"))
    println(nameLength(null))
    returnNothting()
}

fun nameLength(name: String?): Int? {
    val result = try {
        name!!.length
    } catch (ex: Exception) {
        println("Exception : $ex")
        null
    }
    return result
}