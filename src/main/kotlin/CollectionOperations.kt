package org.springkotlin

import data.Course
import data.CourseCategory
import data.KAFKA
import data.courseList

fun main() {
    val courseList = courseList()
    val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPEMENT }
    val desPredicate = { c: Course -> c.category == CourseCategory.DESIGN }
//    exploreFilter(courseList, desPredicate)
    val list = listOf(listOf(1, 2, 3), listOf(4, 5, 6))
    val mapResult = list.map { outerList -> outerList.map { it.toDouble() } }
    println("mapResult: $mapResult")

    val flatMapResult = list.flatMap { outerList -> outerList.map { it.toDouble() } }
    println(flatMapResult)
//    exploreMap(courseList, devPredicate)

    val exploreFlatMap = exploreFlatMap(courseList, KAFKA)
    println(exploreFlatMap)

    exploreHashMap()
    collectionsNullability()
}

fun collectionsNullability() {
    var list: MutableList<String>? = null
    list = mutableListOf()
    list.add("hello")
    list.forEach {
        println("value is $it")
    }

    val list1: List<String?> = listOf("hello", null, "Alex")
    list1.forEach { println("value length is ${it?.length}") }
}

fun exploreHashMap() {
    val mutableMapOf = mutableMapOf("a" to 1, "ab" to 2, "cde" to 3)
    mutableMapOf.forEach { (key, value) -> println("key is $key : value is $value") }

    val value = mutableMapOf.getOrElse("d") { "empty" }
    println("value of d is : $value")

    val containsKey = mutableMapOf.containsKey("abc")
    println("containsKey? : $containsKey")

    val filteredMap = mutableMapOf.filterKeys { it.length > 2 }.map { it.key.uppercase() }
    println("filteredMap : $filteredMap")

    val maxNumber = mutableMapOf.maxByOrNull { it.value }
    println("maxNumber : $maxNumber")
}

fun exploreFlatMap(courseList: List<Course>, kafka: String): List<String> {
    val kafkaList = courseList.flatMap { course ->
//        val courseName = course.name
        course.topicsCovered.filter { it == kafka }.map { course.name }
    }
    return kafkaList
}

fun exploreMap(courseList: List<Course>, predicate: (Course) -> Boolean) {
    val nameList = courseList.filter(predicate).map { "${it.name} - ${it.category}" }.forEach { println(it) }
    println("course :$nameList")
}

fun exploreFilter(courseList: List<Course>, predicate: (Course) -> Boolean) {
    val developmentCourseList = courseList
//        .filter { it.category == CourseCategory.DEVELOPEMENT }
        .filter { predicate.invoke(it) }.forEach {
            println(it)
        }

}