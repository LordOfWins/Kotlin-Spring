package org.springkotlin

data class Course(
    val id: Int,
    val name: String,
    val author: String,
    var courseCategory: CourseCategory = CourseCategory.DEVELOPEMENT
)

enum class CourseCategory {
    DEVELOPEMENT,
    BUSINESS,
    DESIGN,
    MARKETING
}

fun main() {

    val course = Course(1, "Reactive Programming in Modern Java using Project Reactor", "Dilip")

    println(course)

    val course1 = Course(1, "Reactive Programming in Modern Java using Project Reactor", "Dilip")

    println("Checking ~")

    val course3 = course1.copy(

        id = 3, author = "D~"

    )

    println(course3)

    val marketingCourse = Course(1, "FaceBook Marketing", "Dilip", CourseCategory.MARKETING)

    println(marketingCourse)

}