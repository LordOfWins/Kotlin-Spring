package org.springkotlin

data class Movie(
    val id: Int?,
    val name: String
)

fun printName1(name: String?) {
    println("Name is : $name")
}

fun main() {
    fun printName(name: String) {
        println("Name is : $name")
    }

    var nameNullable: String? = null
    nameNullable?.run {
        printName(this)
    }
    nameNullable = "Seungjae"
    val length = nameNullable.length.toLong()
    println("length : $length")

    val nonNullable: String = "Seungjae"
    println(nonNullable)
    printName1(nonNullable)

    val movie = Movie(null, "Seungjae")
    val savedMovie = saveMovie(movie)
    println(savedMovie.id!!)
    println("SavedMovie : $savedMovie")
}

fun saveMovie(movie: Movie): Movie {
    return movie.copy(id = 1)
//    return movie
}