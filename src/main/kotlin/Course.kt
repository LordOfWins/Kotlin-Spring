package org.springkotlin

data class Course(
    val id: Int,
    val name: String,
    val author: String,
)

fun main() {
    val course = Course(1, "Kotlin", "seungjae")
    println(course)
    val course2 = Course(1, "Kotlin", "seungjae")
    println(course2 == course)

    val course3 = Course(2, "Kotlin", "seungjae")
    println(course3 == course)

    val course4 = course.copy(id = 2, name = "Spring", author = "Alex")
    println(course4)
}