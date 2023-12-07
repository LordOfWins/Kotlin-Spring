package org.springkotlin

import data.Course
import data.CourseCategory
import data.courseList

fun main() {
    val nameList = listOf("alex", "ben", "chloe").asSequence()
        .filter { it.length >= 4 } // ["alex","chloe"]
        .map { it.uppercase() } // ["ALEX","CHLOE"]
        .toList() //sequence is converted to list

    println("nameList : $nameList")
    val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPEMENT }

    exploreFilterSequence(courseList(), devPredicate)

    val range = 1..1000_000_000
    range
        .asSequence()
        .map { it.toDouble() }.take(40).forEach { println(it) }
}

fun exploreFilterSequence(courseList: List<Course>, predicate: (Course) -> Boolean) {
    courseList.asSequence().filter { predicate.invoke(it) }.forEach { println("Courses: $it") }
}